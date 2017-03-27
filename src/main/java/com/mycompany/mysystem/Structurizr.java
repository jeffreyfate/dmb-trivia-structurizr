package com.mycompany.mysystem;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.PaperSize;
import com.structurizr.view.Styles;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;

/**
 * This is a simple example of how to get started with Structurizr for Java.
 */
public class Structurizr {

    private static final String API_KEY = "b45ce933-f8e5-4bf5-8a0e-88190b7e0714";
    private static final String API_SECRET = "8e452f58-8120-4bba-9a3b-7bf5eb6b8e41";
    private static final long WORKSPACE_ID = 29571;

    public static void main(String[] args) throws Exception {
        // a Structurizr workspace is the wrapper for a software architecture model, views and documentation
        Workspace workspace = new Workspace("DMB Trivia", "The entire DMB Trivia ecosystem.");

        Model model = workspace.getModel();
        ViewSet viewSet = workspace.getViews();
        Styles styles = viewSet.getConfiguration().getStyles();

        // add some elements to your software architecture model
        Person user = model.addPerson("App User", "A user of the DMB Setlist & Trivia app.");
        SoftwareSystem softwareSystem = model.addSoftwareSystem("DMB Setlist & Trivia", "Android app.");
        user.uses(softwareSystem, "Uses");


        // define some views (the diagrams you would like to see)
        SystemContextView contextView = viewSet.createSystemContextView(softwareSystem, "Context", "A description of this diagram.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();
        contextView.setPaperSize(PaperSize.A5_Landscape);

        // optionally, add some styling
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#08427b").color("#ffffff");

        uploadWorkspaceToStructurizr(workspace);
    }

    private static void uploadWorkspaceToStructurizr(Workspace workspace) throws Exception {
        StructurizrClient structurizrClient = new StructurizrClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }

}