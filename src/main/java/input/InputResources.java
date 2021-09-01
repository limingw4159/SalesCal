package input;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class InputResources {

    public List<String> inputString() throws IOException {
        BufferedReader br = null;
        List<String> userInput = null;
        String readLine;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            userInput = new ArrayList<>();
            while (!(readLine = br.readLine()).equals("exit")) {
                userInput.add(readLine);
            }
        } catch (Exception e) {
            log.error("your input is wrong");
        } finally {
            br.close();
        }
        return userInput;
    }
}
