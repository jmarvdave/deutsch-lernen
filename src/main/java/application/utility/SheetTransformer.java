package application.utility;

import application.model.SheetRow;

import java.util.List;

public interface SheetTransformer {
    List<? extends SheetRow> transform(List<List<Object>> rawData);
}
