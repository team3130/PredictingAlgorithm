import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class TBADataRetriever {
    public static void main(String[] args) {
        // Replace <YOUR_API_KEY> with your actual TBA API key
        String API_KEY = "lMPEvgYHeEP3ivtb8308yTqtIUmGeVXGeu5NRDvNpjog61NWrrdskKJQ1P8UlcGD";

        // Define the API endpoint and parameters
        String endpoint = "https://www.thebluealliance.com/api/v3/";
        String method = "team/%s/simple";
        String requestMethod = "GET";
        String requestHeaders = "X-TBA-Auth-Key=" + API_KEY;

        // Ask the user to input a team number
        System.out.print("Enter team number: ");
        Scanner scanner = new Scanner(System.in);
        String teamNumber = scanner.nextLine();
        scanner.close();

        try {
            // Create the HTTP connection to the API endpoint
            URL url = new URL(endpoint + String.format(method, teamNumber));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("X-TBA-Auth-Key", API_KEY);

            // Read the API response into a string
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            // Parse the JSON response into a Java object
            JSONObject data = new JSONObject(responseBuilder.toString());

            // Print the retrieved data
            System.out.println(data.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}