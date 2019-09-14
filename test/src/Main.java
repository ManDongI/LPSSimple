import com.rmpi.lpssimple.LinearConstraint;
import com.rmpi.lpssimple.LinearProgramingSolver;

import java.util.ArrayList;

import static com.rmpi.lpssimple.OptimizationMode.MINIMIZE;

public class Main {
    public static void main(String[] args) {
        LinearProgramingSolver solver = new LinearProgramingSolver();
        solver.constraints.addAll(new ArrayList<>() {{
            add(new LinearConstraint(2, 3, -12));
            add(new LinearConstraint(2, 1, -8));
            add(new LinearConstraint(1, 0, 0));
            add(new LinearConstraint(0, 1, 0));
        }});
        solver.target = new LinearConstraint(300, 200, 0);
        solver.optimizationMode = MINIMIZE;
        solver.solve();
        System.out.println(solver.getArgX().toPlainString() + ' ' + solver.getArgY().toPlainString() + ' ' + solver.getValue().toPlainString());
        solver.constraints.clear();
        solver.constraints.addAll(new ArrayList<>() {{
            add(new LinearConstraint(1, -2, 0));
            add(new LinearConstraint(1, 2, -100));
            add(new LinearConstraint(1, 0, 0));
            add(new LinearConstraint(0, 1, 0));
        }});
        solver.target = new LinearConstraint(29, 16, 0);
        solver.optimizationMode = MINIMIZE;
        solver.solve();
        System.out.println(solver.getArgX().toPlainString() + ' ' + solver.getArgY().toPlainString() + ' ' + solver.getValue().toPlainString());
    }
}
