package com.qc.skillscy.commons.misc;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.qc.skillscy.commons.codes.ApplicationCodes;
import com.qc.skillscy.commons.codes.HTTPCodes;
import com.qc.skillscy.commons.dto.LookupDocument;
import com.qc.skillscy.commons.dto.PeopleLookupDocument;
import com.qc.skillscy.commons.exceptions.WebExceptionType;
import com.qc.skillscy.commons.exceptions.WebServiceException;
import com.qc.skillscy.commons.loggers.CommonLogger;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseDB {

    private static FirebaseDB firebaseDB;
    private final String LOOKUP_DOCUMENT_NAME = "lookup";

    public static FirebaseDB connect() {
        if (Validator.isNull(FirebaseDB.firebaseDB))
            FirebaseDB.firebaseDB = new FirebaseDB();
        return FirebaseDB.firebaseDB;
    }

    public String getLatestIDFromLookupDocument(Firestore firestore, String collectionName) throws WebServiceException {
        CommonLogger.info(FirebaseDB.class, "Connecting to Firebase Firestore for retrieving Lookup document...");
        ApiFuture<DocumentSnapshot> future = firestore.collection(collectionName).document(this.LOOKUP_DOCUMENT_NAME).get();
        DocumentSnapshot document;

        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_EXCEPTION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.DATABASE_ERROR);
        }

        if (Validator.isNotNull(document)) {
            if (document.exists() && document.getData() != null) {
                LookupDocument lookupDocument = new LookupDocument(document.getData());
                CommonLogger.info(FirebaseDB.class, "Lookup document received");
                if (Validator.isNotNull(lookupDocument.getLatestID())) {
                    CommonLogger.info(FirebaseDB.class, "Returning Latest ID value from Lookup document");
                    return lookupDocument.getLatestID();
                } else {
                    throw new WebServiceException(ApplicationCodes.DOCUMENT_LOOKUP_LATEST_ID_NOT_FOUND, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
                }
            } else {
                throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_NOT_RETRIEVED, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
            }
        } else {
            throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_NOT_EXIST, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
        }
    }

    public void updateLatestIDToLookupDocument(Firestore firestore, String collectionName, String newIDValue) throws WebServiceException {
        CommonLogger.info(FirebaseDB.class, "Connecting to Firebase Firestore for updating Lookup document with new ID [".concat(newIDValue).concat("]..."));
        ApiFuture<?> future = firestore.collection(collectionName).document(this.LOOKUP_DOCUMENT_NAME).update(QcCommonConstants.LOOKUP_DOC_LATEST_ID, newIDValue);
        WriteResult writeResult;

        try {
            writeResult = (WriteResult) future.get();
            CommonLogger.info(FirebaseDB.class, "Lookup document successfully written to DB [".concat(QcUtils.objectToJsonString(writeResult)).concat("]"));
        } catch (InterruptedException | ExecutionException e) {
            throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_EXCEPTION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.DATABASE_ERROR);
        }
    }

    public List<String> getAvailableProjectIDsFromLookupDocument(Firestore firestore, String collectionName) throws WebServiceException {
        CommonLogger.info(FirebaseDB.class, "Connecting to Firebase Firestore for retrieving Lookup document...");
        ApiFuture<DocumentSnapshot> future = firestore.collection(collectionName).document(this.LOOKUP_DOCUMENT_NAME).get();
        DocumentSnapshot document;

        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_EXCEPTION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.DATABASE_ERROR);
        }

        if (Validator.isNotNull(document)) {
            if (document.exists() && document.getData() != null) {
                LookupDocument lookupDocument = new LookupDocument(document.getData());
                CommonLogger.info(FirebaseDB.class, "Lookup document received");
                if (Validator.isNotNull(lookupDocument.getAvailableProjectIDs())) {
                    CommonLogger.info(FirebaseDB.class, "Returning List of available product IDs value from Lookup document");
                    return lookupDocument.getAvailableProjectIDs();
                } else {
                    throw new WebServiceException(ApplicationCodes.DOCUMENT_LOOKUP_AVAILABLE_PROJECT_IDS_NOT_FOUND, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
                }
            } else {
                throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_NOT_RETRIEVED, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
            }
        } else {
            throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_NOT_EXIST, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
        }
    }

    public void updateAvailableProjectIDsToLookupDocument(Firestore firestore, String collectionName, String newIDValue) throws WebServiceException {
        CommonLogger.info(FirebaseDB.class, "Connecting to Firebase Firestore for updating Lookup document with new availble Project ID [".concat(newIDValue).concat("]..."));
        ApiFuture<?> future = firestore.collection(collectionName).document(this.LOOKUP_DOCUMENT_NAME).update(QcCommonConstants.LOOKUP_DOC_AVAILABLE_IDs, FieldValue.arrayUnion(newIDValue));
        WriteResult writeResult;

        try {
            writeResult = (WriteResult) future.get();
            CommonLogger.info(FirebaseDB.class, "Lookup document successfully written to DB [".concat(QcUtils.objectToJsonString(writeResult)).concat("]"));
        } catch (InterruptedException | ExecutionException e) {
            throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_EXCEPTION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.DATABASE_ERROR);
        }
    }

    public PeopleLookupDocument getPeopleLatestIDsFromLookupDocument(Firestore firestore, String collectionName) throws WebServiceException {
        CommonLogger.info(FirebaseDB.class, "Connecting to Firebase Firestore for retrieving Lookup document...");
        ApiFuture<DocumentSnapshot> future = firestore.collection(collectionName).document(this.LOOKUP_DOCUMENT_NAME).get();
        DocumentSnapshot document;

        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_EXCEPTION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.DATABASE_ERROR);
        }

        if (Validator.isNotNull(document)) {
            if (document.exists() && document.getData() != null) {
                PeopleLookupDocument lookupDocument = new PeopleLookupDocument(document.getData());
                CommonLogger.info(FirebaseDB.class, "Lookup document received");
                if (Validator.isNotNull(lookupDocument.getLatestIDOfEmployee()) && Validator.isNotNull(lookupDocument.getLatestIDOfIntern()) && Validator.isNotNull(lookupDocument.getLatestIDOfTrainee())) {
                    CommonLogger.info(FirebaseDB.class, "Returning all people's Latest ID values as an object from Lookup document");
                    return lookupDocument;
                } else {
                    throw new WebServiceException(ApplicationCodes.DOCUMENT_LOOKUP_PEOPLE_LATEST_IDs_NOT_FOUND, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
                }
            } else {
                throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_NOT_RETRIEVED, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
            }
        } else {
            throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_NOT_EXIST, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
        }
    }

    public void updatePeopleLatestIDsToLookupDocument(Firestore firestore, String collectionName, PeopleLookupDocument newIDValue) throws WebServiceException {
        CommonLogger.info(FirebaseDB.class, "Connecting to Firebase Firestore for updating Lookup document with new ID [".concat(QcUtils.objectToJsonString(newIDValue)).concat("]..."));
        ApiFuture<?> future = firestore.collection(collectionName).document(this.LOOKUP_DOCUMENT_NAME).update(newIDValue.dbValue());
        WriteResult writeResult;

        try {
            writeResult = (WriteResult) future.get();
            CommonLogger.info(FirebaseDB.class, "Lookup document successfully written to DB [".concat(QcUtils.objectToJsonString(writeResult)).concat("]"));
        } catch (InterruptedException | ExecutionException e) {
            throw new WebServiceException(ApplicationCodes.FIREBASE_DOCUMENT_EXCEPTION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.DATABASE_ERROR);
        }
    }

}
