package org.jukeboxmc.form.element;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.jukeboxmc.form.CustomForm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GoMint
 * @version 1.0
 */
public class Dropdown extends Element  {

    private final CustomForm form;
    private final List<String> options = new ArrayList<>();
    private int defaultOption = 0;

    public Dropdown( CustomForm customForm, String id, String text ) {
        super( id, text );
        this.form = customForm;
    }

    public Dropdown addOption( String option ) {
        this.options.add( option );
        this.form.setDirty();
        return this;
    }

    public Dropdown addOption( String option, boolean defaultOption ) {
        if ( defaultOption ) {
            this.defaultOption = this.options.size();
        }

        this.options.add( option );
        this.form.setDirty();
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject obj = super.toJSON();
        obj.put( "type", "dropdown" );

        JSONArray jsonOptions = new JSONArray();
        jsonOptions.addAll( this.options );

        obj.put( "options", jsonOptions );
        obj.put( "default", this.defaultOption );
        return obj;
    }

    @Override
    public Object getAnswer( Object answerOption ) {
        long optionIndex = (long) answerOption;
        this.defaultOption = (int) optionIndex;
        return this.options.get( (int) optionIndex );
    }

}