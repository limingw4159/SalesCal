import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class InputResources {
    public List<String> inputString() throws IOException {
        List<String> userInput = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 3; i++) {
            userInput.add(br.readLine());
        }
        br.close();
        return userInput;
    }


}
