module com.mosquida.solutionengine {
    requires javafx.controls;
    requires javafx.fxml;
    requires MathParser.org.mXparser;

    opens com.mosquida.solutionengine.models to javafx.base;
    opens com.mosquida.solutionengine to javafx.fxml;
    exports com.mosquida.solutionengine;
}