package holycode.phone_operator_app.services.impl;

import com.opencsv.CSVReader;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.orientechnologies.orient.server.distributed.ODistributedServerLog.DIRECTION;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import holycode.phone_operator_app.requests.CallSearchRequest;
import holycode.phone_operator_app.responses.CallSearchResponse;
import holycode.phone_operator_app.services.OperatorService;

/**
 * Implementation class for {@link OperatorService}.
 * 
 * @author Aleksandar Komarica
 *
 */
@Service
public class OperatorServiceImpl implements OperatorService {
  
  @Value("${database.location}")
  private String databaseLocation;

  OrientGraph graph;
  
  private static final String SAMPLE_CSV_FILE_PATH = "src/main/resources/cdr_20000_1.csv";

	/*
	 * (non-Javadoc)
	 * 
	 * @see holycode.phone_operator_app.services.OperatorService#
	 * populateCallSearchResponses
	 * (holycode.phone_operator_app.requests.CallSearchRequest)
	 */
	@Override
	public List<CallSearchResponse> populateCallSearchResponses(CallSearchRequest callSearchRequest) {
	  List<CallSearchResponse> callSearchResponses = new ArrayList<>();
	  graph = getGraph();
	  Direction direction=null;
	  switch (callSearchRequest.getCallDirection()) {
	    case "incoming" : direction = Direction.IN;
	      break;
        case "outgoing" : direction = Direction.OUT;
          break;
        case "all" : direction = Direction.BOTH;
          break;
	  }
	  for (Vertex v : (Iterable<Vertex>) graph.command(
	        new OCommandSQL(
	            "SELECT * FROM Phone "
	          + "WHERE NUMBER='"+ callSearchRequest.getPhoneNumber()+"'")).execute()) {
	    String numberCaller = v.getProperty("NUMBER");
	    String userCaller = v.getProperty("USER");
	    String addressCaller = v.getProperty("ADDRESS");
	    Iterable<Edge> edges = v.getEdges(direction, "call");
	    Iterator<Edge> iterator = edges.iterator();
	    while (iterator.hasNext()) {
          Edge edge = (Edge) iterator.next();
          Date callDate = edge.getProperty("EVENT_DATE");
          String duration = edge.getProperty("DURATION");
          
          Vertex vertex = edge.getVertex(direction == Direction.IN ? Direction.OUT : Direction.IN);
          if (vertex.getProperty("NUMBER").equals(v.getProperty("NUMBER"))) {
            vertex = edge.getVertex(Direction.OUT);
          }
          String numberReciever = vertex.getProperty("NUMBER");
          String userReciever = vertex.getProperty("USER");
          String addressReciever = vertex.getProperty("ADDRESS");
          
          Date dateFrom = null;
          Date dateTo = null;
          
          try {
            if (!callSearchRequest.getDateFrom().isEmpty())
              dateFrom=new SimpleDateFormat("dd-MM-yyyy").parse(callSearchRequest.getDateFrom());
            if (!callSearchRequest.getDateTo().isEmpty())
              dateTo=new SimpleDateFormat("dd-MM-yyyy").parse(callSearchRequest.getDateTo());
          } catch (ParseException e) {
            e.printStackTrace();
          }
          
          if ((dateFrom == null && dateTo == null) || 
              (dateFrom != null && dateTo != null && dateFrom.before(callDate) && dateTo.after(callDate)) ||
              (dateFrom == null && dateTo != null && dateTo.after(callDate)) ||
              (dateFrom != null && dateTo == null && dateFrom.before(callDate))) {
            callSearchResponses.add(new CallSearchResponse(numberCaller, numberReciever, duration, callDate, userCaller, addressCaller, userReciever, addressReciever));
          }
          
        }
	  }
	  
		
		return callSearchResponses;
	}

  @Override
  public String initializeDB() {

    graph = getGraph();

    if (graph.getVertexType("Phone") == null) {

      graph.createVertexType("Phone");
      Vertex vPhoneA;
      Vertex vPhoneB;
      OrientEdge eCall;
      try (Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
          CSVReader csvReader = new CSVReader(reader, ';');
      ) {
        String[] header = csvReader.readNext();
 
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
          System.out.println("Phone No : " + nextRecord[2]);
          System.out.println("==========================");
          
          Iterable<Vertex> vertices = graph.getVertices("NUMBER", nextRecord[2]);
          if (!vertices.iterator().hasNext()) {
            vPhoneA = graph.addVertex("class:Phone");
          }
          else {
            vPhoneA = vertices.iterator().next();
          }
          
          Iterable<Vertex> verticesB = graph.getVertices("NUMBER", nextRecord[7]);
          if (!verticesB.iterator().hasNext()) {
            vPhoneB = graph.addVertex("class:Phone");
          }
          else {
            vPhoneB = verticesB.iterator().next();
          }
          

          String duration = null;
          String dateString = null;
          
          for (int i = 0; i < nextRecord.length - 1; i++) {
              switch (header[i]) {
                case "EVENT_DATE": dateString = nextRecord[i];
                break;
                case "A_NUMBER": vPhoneA.setProperty("NUMBER", nextRecord[i]);
                break;
                case "A_User": vPhoneA.setProperty("USER", nextRecord[i]);
                break;
                case "FILL35": vPhoneA.setProperty("ADDRESS", nextRecord[i]);
                break;
              
                case "B_NUMBER": vPhoneB.setProperty("NUMBER", nextRecord[i]);
                break;
                case "B_User": vPhoneB.setProperty("USER", nextRecord[i]);
                break;
                case "FILL57": vPhoneB.setProperty("ADDRESS", nextRecord[i]);
                break;
                case "DURATION": duration = nextRecord[i];
                break;
              }
              
          }

          eCall = graph.addEdge("class:calls", vPhoneA, vPhoneB, "call");
          Date dateFormatted=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);  
          
          eCall.setProperties("EVENT_DATE", dateFormatted);
          eCall.setProperties("DURATION", duration);

          graph.commit();
        }
      } catch (IOException e) {
        e.printStackTrace();
        graph.rollback();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    } else {
      return "There is already imported DATA !";
    }

    //graph.shutdown();
    return "Imported into OrientDB!";
  }
  
  private OrientGraph getGraph () {
    if (graph == null) {
      graph = new OrientGraph("plocal:"+databaseLocation, "admin", "admin");
    }
    return graph;
  }

}