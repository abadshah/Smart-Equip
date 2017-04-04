package com.alkesh;

import java.util.Arrays;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class NumbersController {

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public String prompt() {
        String numbers = Arrays.toString(Numbers.generate()).replace("[", "").replace("]", "");
        return "Please sum the numbers: " + numbers;
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> processResponse(@RequestParam(value="question", required=true) String question,
                                                  @RequestParam(value="answer", required=true) String answer) {

        int[] questionInt = Numbers.stringToArray(answer);
        if (questionInt == null || questionInt.length <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Don't mess with me. Please try again.");
        }

        if (!Numbers.isCorrectResponse(NumberUtils.toInt(answer), questionInt)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("That's wrong. Please try again.");
        }
        return ResponseEntity.status(HttpStatus.OK)
                             .body("That's great. " + answer + " is the right answer");
    }
}
