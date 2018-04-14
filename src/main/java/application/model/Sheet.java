package application.model;

import java.util.List;

interface Sheet {
    List<? extends SheetRow> getValues();
}
