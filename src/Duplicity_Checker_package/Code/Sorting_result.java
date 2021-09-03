package Duplicity_Checker_package.Code;

import java.io.IOException;

public class Sorting_result {
    String string_temp1, string_temp2;
    float temp;
    int p, q;

    public Object[] sort(String[] FILENAME3, String[] FILENAME4, float[] matching_line_number, float[] result, int result_counter) throws IOException {
        for (p = 0; p < result_counter; p++) {
            for (q = p + 1; q < result_counter; q++) {
                if (result[p] < result[q]) {

                    temp = result[q];
                    result[q] = result[p];
                    result[p] = temp;

                    temp = matching_line_number[q];
                    matching_line_number[q] = matching_line_number[p];
                    matching_line_number[p] = temp;

                    string_temp1 = FILENAME3[q];
                    FILENAME3[q] = FILENAME3[p];
                    FILENAME3[p] = string_temp1;

                    string_temp2 = FILENAME4[q];
                    FILENAME4[q] = FILENAME4[p];
                    FILENAME4[p] = string_temp2;
                }
            }
        }
        return new Object[] {FILENAME3,FILENAME4,matching_line_number,result,result_counter};
    }
}