package application.model;

import java.util.List;

interface Sheet {
    public List<? extends SheetRow> getValues();
}
