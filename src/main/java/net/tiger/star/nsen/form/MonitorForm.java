package net.tiger.star.nsen.form;


import java.util.ArrayList;
import java.util.List;

public class MonitorForm extends AbstractBaseForm {

    public List<String> videos;

    @Override
    void resetForm() {
        videos = new ArrayList<>();
    }

}