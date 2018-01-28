package holycode.phone_operator_app.services.impl;

import holycode.phone_operator_app.requests.CallSearchRequest;
import holycode.phone_operator_app.responses.CallSearchResponse;
import holycode.phone_operator_app.services.OperatorService;

import com.opencsv.CSVReader;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Implementation class for {@link OperatorService}.
 * 
 * @author Aleksandar Komarica
 *
 */
@Service
public class OperatorServiceImpl implements OperatorService {

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
		callSearchResponses.add(new CallSearchResponse("111", new Date()));
		callSearchResponses.add(new CallSearchResponse("222", new Date()));
		callSearchResponses.add(new CallSearchResponse("333", new Date()));
		return callSearchResponses;
	}

  @Override
  public void initializeDB() {

    OrientGraph graph = new OrientGraph("plocal:C:\\apps\\orientdb-2.2.31\\databases\\test2", 
          "root", "password");
    
    graph.createVertexType("Phone");
    Vertex vPhone;
    
    try (
        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        CSVReader csvReader = new CSVReader(reader,';');
        
    ) {
        String[] header = csvReader.readNext();
        // Reading Records One by One in a String array
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            System.out.println("Name : " + nextRecord[0]);
            System.out.println("Email : " + nextRecord[1]);
            System.out.println("==========================");
            
            System.out.println(header.length + " " + nextRecord.length);
            vPhone = graph.addVertex("class:Phone");
            for (int i=0; i<nextRecord.length-1; i++) {
              if (header[i].contains(",")) 
                header[i]=header[i].replaceAll(",", "-");
              vPhone.setProperty(header[i], nextRecord[i]);
            }

            graph.commit();
        }
    } catch (IOException e) {
      e.printStackTrace();
      graph.rollback();
    }
    graph.shutdown();
  }

}