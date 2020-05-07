package com.fkhwl.scf.third.resp.logink;

import java.io.*;

public class HeaderBean implements Serializable {
    private static final long serialVersionUID = -500425649239942986L;
    /**
         * MessageReferenceNumber :
         * DocumentName :
         * DocumentVersionNumber :
         * SenderCode :
         * MessageSendingDateTime :
         */

        private String MessageReferenceNumber;
        private String DocumentName;
        private String DocumentVersionNumber;
        private String SenderCode;
        private String MessageSendingDateTime;

        public String getMessageReferenceNumber() {
            return MessageReferenceNumber;
        }

        public void setMessageReferenceNumber(String MessageReferenceNumber) {
            this.MessageReferenceNumber = MessageReferenceNumber;
        }

        public String getDocumentName() {
            return DocumentName;
        }

        public void setDocumentName(String DocumentName) {
            this.DocumentName = DocumentName;
        }

        public String getDocumentVersionNumber() {
            return DocumentVersionNumber;
        }

        public void setDocumentVersionNumber(String DocumentVersionNumber) {
            this.DocumentVersionNumber = DocumentVersionNumber;
        }

        public String getSenderCode() {
            return SenderCode;
        }

        public void setSenderCode(String SenderCode) {
            this.SenderCode = SenderCode;
        }

        public String getMessageSendingDateTime() {
            return MessageSendingDateTime;
        }

        public void setMessageSendingDateTime(String MessageSendingDateTime) {
            this.MessageSendingDateTime = MessageSendingDateTime;
        }
    }
