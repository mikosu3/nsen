package net.tiger.star.nsen.form;




public class SearchForm extends AbstractBaseForm {

    private static final long serialVersionUID = 1L;

    public String video = "";

    @Override
    public void resetForm() {
        page = 1;
    }

}