package org.example;

import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener{

    @Override
    public void newRevision(Object revisionEntity) { final Revision revision = (Revision) revisionEntity;}
    }
