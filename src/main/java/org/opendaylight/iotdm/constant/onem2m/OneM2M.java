package org.opendaylight.iotdm.constant.onem2m;

import java.math.BigInteger;

/**
 * Created by wenxshi on 4/2/15.
 */
public class OneM2M {

    public enum Operation {
        CREATE(BigInteger.valueOf(1)),
        RETRIEVE(BigInteger.valueOf(2)),
        UPDATE(BigInteger.valueOf(3)),
        DELETE(BigInteger.valueOf(4)),
        NOTIFY(BigInteger.valueOf(5));

        private BigInteger operation;

        Operation(BigInteger operation) {
            this.operation = operation;
        }

        private BigInteger getValue() {
            return operation;
        }

        public static Operation getEnum(BigInteger value) {
            for (Operation o : Operation.values()) {
                if (o.getValue().equals(value)) {
                    return o;
                }
            }
            return null;
        }
    }


    public enum ConsistencyStrategy {
        ABANDON_MEMBER(BigInteger.valueOf(1)),
        ABANDON_GROUP(BigInteger.valueOf(2)),
        SET_MIXED(BigInteger.valueOf(3));

        private BigInteger value;

        ConsistencyStrategy(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum EncodingType {
        PLAIN(BigInteger.valueOf(0)),
        BASE64_ON_STRING(BigInteger.valueOf(1)),
        BASE64_ON_BINARY(BigInteger.valueOf(2));


        private BigInteger value;

        EncodingType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum EventType {
        DATAOPERATION(BigInteger.valueOf(1)),
        STORAGEBASED(BigInteger.valueOf(2)),
        TIMERBASED(BigInteger.valueOf(3));


        private BigInteger value;

        EventType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum ExecModeType {
        IMMEDIATEONCE(BigInteger.valueOf(1)),
        IMMEDIATEREPEAT(BigInteger.valueOf(2)),
        RANDOMONCE(BigInteger.valueOf(3)),
        RANDOMREPEAT(BigInteger.valueOf(4));


        private BigInteger value;

        ExecModeType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum ExecStatusType {
        INITIATED(BigInteger.valueOf(1)),
        PENDING(BigInteger.valueOf(2)),
        FINISHED(BigInteger.valueOf(3)),
        CANCELLING(BigInteger.valueOf(4)),
        CANCELLED(BigInteger.valueOf(5)),
        STATUS_NON_CANCELLABLE(BigInteger.valueOf(6));


        private BigInteger value;

        ExecStatusType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum LocationSource {
        NETWORK_BASED(BigInteger.valueOf(1)),
        DEVICE_BASED(BigInteger.valueOf(2)),
        SHARING_BASED(BigInteger.valueOf(3));


        private BigInteger value;

        LocationSource(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }


    public enum LogStatus {
        STARTED(BigInteger.valueOf(1)),
        STOPPED(BigInteger.valueOf(2)),
        UNKNOWN(BigInteger.valueOf(3)),
        NOT_PRESENT(BigInteger.valueOf(4)),
        ERROR(BigInteger.valueOf(5));


        private BigInteger value;

        LogStatus(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum LogTypeId {
        SYSTEM(BigInteger.valueOf(1)),
        SECURITY(BigInteger.valueOf(2)),
        EVENT(BigInteger.valueOf(3)),
        TRACE(BigInteger.valueOf(4)),
        PANIC(BigInteger.valueOf(5));


        private BigInteger value;

        LogTypeId(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum MemberType {
        ACCESS_CONTROL_POLICY(BigInteger.valueOf(1)),
        AE(BigInteger.valueOf(2)),
        CONTAINER(BigInteger.valueOf(3)),
        CONTENT_INSTANCE(BigInteger.valueOf(4)),
        CSE_BASE(BigInteger.valueOf(5)),
        DELIVERY(BigInteger.valueOf(6)),
        EVENT_CONFIG(BigInteger.valueOf(7)),
        EXEC_INSTANCE(BigInteger.valueOf(8)),
        GROUP(BigInteger.valueOf(9)),
        LOCATION_POLICY(BigInteger.valueOf(10)),
        M2M_SERVICE_SUBSCRIPTION(BigInteger.valueOf(11)),
        MGMT_CMD(BigInteger.valueOf(12)),
        MGMT_OBJ(BigInteger.valueOf(13)),
        NODE(BigInteger.valueOf(14)),
        POLLING_CHANNEL(BigInteger.valueOf(15)),
        REMOTE_CSE(BigInteger.valueOf(16)),
        REQUEST(BigInteger.valueOf(17)),
        SCHEDULE(BigInteger.valueOf(18)),
        SERVICE_SUBSCRIBED_APP_RULE(BigInteger.valueOf(19)),
        SERVICE_SUBSCRIBED_NODE(BigInteger.valueOf(20)),
        STATS_COLLECT(BigInteger.valueOf(21)),
        STATS_CONFIG(BigInteger.valueOf(22)),
        SUBSCRIPTION(BigInteger.valueOf(23)),
        MIXED(BigInteger.valueOf(24));


        private BigInteger value;

        MemberType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum MgmtDefinition {
        FIRMWARE(BigInteger.valueOf(1001)),
        SOFTWARE(BigInteger.valueOf(1002)),
        MEMORY(BigInteger.valueOf(1003)),
        AREA_NWK_INFO(BigInteger.valueOf(1004)),
        AREA_NWK_DEVICE_INFO(BigInteger.valueOf(1005)),
        BATTERY(BigInteger.valueOf(1006)),
        DEVICE_INFO(BigInteger.valueOf(1007)),
        DEVICE_CAPABILITY(BigInteger.valueOf(1008)),
        REBOOT(BigInteger.valueOf(1009)),
        EVENT_LOG(BigInteger.valueOf(1010)),
        CMDH_POLICY(BigInteger.valueOf(1011)),
        ACTIVE_CMDH_POLICY(BigInteger.valueOf(1012)),
        CMDH_DEFAULTS(BigInteger.valueOf(1013)),
        CMDH_DEF_EC_VALUE(BigInteger.valueOf(1014)),
        CMDH_EC_DEF_PARAM_VALUES(BigInteger.valueOf(1015)),
        CMDH_LIMITS(BigInteger.valueOf(1016)),
        CMDH_NETWORK_ACCESS_RULES(BigInteger.valueOf(1017)),
        CMDH_NW_ACCESS_RULE(BigInteger.valueOf(1018)),
        CMDH_BUFFER(BigInteger.valueOf(1019)),
        UNSPECIFIED(BigInteger.valueOf(0));


        private BigInteger value;

        MgmtDefinition(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum NotificationContentType {
        MODIFIED_ATTRIBUTES(BigInteger.valueOf(1)),
        WHOLE_RESOURCE(BigInteger.valueOf(2)),
        REFERENCE_ONLY(BigInteger.valueOf(3));


        private BigInteger value;

        NotificationContentType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum PendingNotification {
        SEND_LATEST(BigInteger.valueOf(1)),
        SEND_ALL_PENDING(BigInteger.valueOf(2));


        private BigInteger value;

        PendingNotification(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum RequestStatus {
        COMPLETED(BigInteger.valueOf(1)),
        FAILED(BigInteger.valueOf(2)),
        PENDING(BigInteger.valueOf(3)),
        FORWARDED(BigInteger.valueOf(4));


        private BigInteger value;

        RequestStatus(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }

    }

    public enum ResourceStatus {
        CHILD_CREATED(BigInteger.valueOf(1)),
        CHILD_DELETED(BigInteger.valueOf(2)),
        UPDATED(BigInteger.valueOf(3)),
        DELETED(BigInteger.valueOf(4));


        private BigInteger value;

        ResourceStatus(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum SRoleId {
        SOFTWARE_MANAGEMENT("01-001"),
        DEVICE_CONFIGURATION("02-001"),
        DEVICE_DIAGNOSTICS_AND_MANAGEMENT("02-002"),
        DEVICE_FIRMWARE_MANAGEMENT("02-003"),
        DEVICE_TOPOLOGY("02-004"),
        LOCATION("03-001"),
        BASIC_DATA("04-001"),
        ONBOARDING("05-001"),
        SECURITY_ADMINISTRATION("06-001"),
        GROUPS_MANAGEMENT("07-001"),
        EVENT_COLLECTION("08-001");


        private String value;

        SRoleId(String value) {
            this.value = value;
        }


        public String value() {
            return value;
        }
    }

    public enum StatModelType {
        EVENTBASED(BigInteger.valueOf(1));


        private BigInteger value;

        StatModelType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum StatsRuleStatusType {
        ACTIVE(BigInteger.valueOf(1)),
        INACTIVE(BigInteger.valueOf(2));


        private BigInteger value;

        StatsRuleStatusType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum Status {
        SUCCESSFUL(BigInteger.valueOf(1)),
        FAILURE(BigInteger.valueOf(2)),
        IN__PROCESS(BigInteger.valueOf(3));


        private BigInteger value;

        Status(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum StdEventCats {
        DEFAULT(BigInteger.valueOf(1)),
        IMMEDIATE(BigInteger.valueOf(2)),
        BEST_EFFORT(BigInteger.valueOf(3)),
        LATEST(BigInteger.valueOf(4));


        private BigInteger value;

        StdEventCats(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }

    }

    public enum ResultContent {
        NOTHING(BigInteger.valueOf(0)),
        ATTRIBUTES(BigInteger.valueOf(1)),
        HIERARCHICAL_ADDRESS(BigInteger.valueOf(2)),
        HIERARCHICAL_ADDRESS_AND_ATTRIBUTES(BigInteger.valueOf(3)),
        ATTRIBUTES_AND_CHILD_RESOURCES(BigInteger.valueOf(4)),
        ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES(BigInteger.valueOf(5)),
        CHILD_RESOURCE_REFERENCES(BigInteger.valueOf(6)),
        ORIGINAL_RESOURCE(BigInteger.valueOf(7));


        private BigInteger value;

        ResultContent(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum ResponseType {
        NON_BLOCKING_REQUEST_SYNCH(BigInteger.valueOf(1)),
        NON_BLOCKING_REQUEST_ASYNCH(BigInteger.valueOf(2)),
        BLOCKING_REQUEST(BigInteger.valueOf(3));


        private BigInteger value;

        ResponseType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }


    public enum ExecResultType {
        STATUS_REQUEST_UNSUPPORTED(BigInteger.valueOf(1)),
        STATUS_REQUEST_DENIED(BigInteger.valueOf(2)),
        STATUS_CANCELLATION_DENIED(BigInteger.valueOf(3)),
        STATUS_INTERNAL_ERROR(BigInteger.valueOf(4)),
        STATUS_INVALID_ARGUMENTS(BigInteger.valueOf(5)),
        STATUS_RESOURCES_EXCEEDED(BigInteger.valueOf(6)),
        STATUS_FILE_TRANSFER_FAILED(BigInteger.valueOf(7)),
        STATUS_FILE_TRANSFER_SERVER_AUTHENTICATION_FAILURE(BigInteger.valueOf(8)),
        STATUS_UNSUPPORTED_PROTOCOL(BigInteger.valueOf(9)),
        STATUS_UPLOAD_FAILED(BigInteger.valueOf(10)),
        STATUS_FILE_TRANSFER_FAILED_MULTICAST_GROUP_UNABLE_JOIN(BigInteger.valueOf(11)),
        STATUS_FILE_TRANSFER_FAILED_SERVER_CONTACT_FAILED(BigInteger.valueOf(12)),
        STATUS_FILE_TRANSFER_FAILED_FILE_ACCESS_FAILED(BigInteger.valueOf(13)),
        STATUS_FILE_TRANSFER_FAILED_DOWNLOAD_INCOMPLETE(BigInteger.valueOf(14)),
        STATUS_FILE_TRANSFER_FAILED_FILE_CORRUPTED(BigInteger.valueOf(15)),
        STATUS_FILE_TRANSFER_FILE_AUTHENTICATION_FAILURE(BigInteger.valueOf(16)),
        //todo duplicated with 8,9
//    STATUS_FILE_TRANSFER_FAILED( BigInteger.valueOf(17)),
//    STATUS_FILE_TRANSFER_SERVER_AUTHENTICATION_FAILURE(BigInteger.valueOf(18)),
        STATUS_FILE_TRANSFER_WINDOW_EXCEEDED(BigInteger.valueOf(19)),
        STATUS_INVALID_UUID_FORMAT(BigInteger.valueOf(20)),
        STATUS_UNKNOWN_EXECUTION_ENVIRONMENT(BigInteger.valueOf(21)),
        STATUS_DISABLED_EXECUTION_ENVIRONMENT(BigInteger.valueOf(22)),
        STATUS_EXECUTION_ENVIRONMENT_MISMATCH(BigInteger.valueOf(23)),
        STATUS_DUPLICATE_DEPLOYMENT_UNIT(BigInteger.valueOf(24)),
        STATUS_SYSTEM_RESOURCES_EXCEEDED(BigInteger.valueOf(25)),
        STATUS_UNKNOWN_DEPLOYMENT_UNIT(BigInteger.valueOf(26)),
        STATUS_INVALID_DEPLOYMENT_UNIT_STATE(BigInteger.valueOf(27)),
        STATUS_INVALID_DEPLOYMENT_UNIT_UPDATE_DOWNGRADE_DISALLOWED(BigInteger.valueOf(28)),
        STATUS_INVALID_DEPLOYMENT_UNIT_UPDATE_UPGRADE_DISALLOWED(BigInteger.valueOf(29)),
        STATUS_INVALID_DEPLOYMENT_UNIT_UPDATE_VERSION_EXISTS(BigInteger.valueOf(30));


        private BigInteger value;

        ExecResultType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum DiscResType {
        HIERARCHICAL(BigInteger.valueOf(1)),
        NON_HIERARCHICAL(BigInteger.valueOf(2)),
        CSE_ID_AND_RESOURCE_ID(BigInteger.valueOf(3));


        private BigInteger value;

        DiscResType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum CseTypeId {
        IN__CSE(BigInteger.valueOf(1)),
        MN__CSE(BigInteger.valueOf(2)),
        ASN__CSE(BigInteger.valueOf(3));

        private BigInteger value;

        CseTypeId(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum ResponseStatusCodes {
        ACCEPTED(BigInteger.valueOf(1000)),
        OK(BigInteger.valueOf(2000)),
        CREATED(BigInteger.valueOf(2001)),
        DELETED(BigInteger.valueOf(2002)),
        CHANGED(BigInteger.valueOf(2004)),
        BAD_REQUEST(BigInteger.valueOf(4000)),
        NOT_FOUND(BigInteger.valueOf(4004)),
        OPERATION_NOT_ALLOWED(BigInteger.valueOf(4005)),
        REQUEST_TIMEOUT(BigInteger.valueOf(4008)),
        SUBSCRIPTION_CREATOR_HAS_NO_PRIVILEGE_(BigInteger.valueOf(4101)),
        CONTENTS_UNACCEPTABLE(BigInteger.valueOf(4102)),
        ACCESS_DENIED(BigInteger.valueOf(4103)),
        GROUP_REQUEST_IDENTIFIER_EXISTS(BigInteger.valueOf(4104)),
        CONFLICT(BigInteger.valueOf(4105)),
        INTERNAL_SERVER_ERROR(BigInteger.valueOf(5000)),
        NOT_IMPLEMENTED(BigInteger.valueOf(5001)),
        TARGET_NOT_REACHABLE(BigInteger.valueOf(5103)),
        NO_PRIVILEGE(BigInteger.valueOf(5105)),
        ALREADY_EXISTS(BigInteger.valueOf(5106)),
        TARGET_NOT_SUBSCRIBABLE(BigInteger.valueOf(5203)),
        SUBSCRIPTION_VERIFICATION_INITIATION_FAILED(BigInteger.valueOf(5204)),
        SUBSCRIPTION_HOST_HAS_NO_PRIVILEGE(BigInteger.valueOf(5205)),
        NON_BLOCKING_REQUEST_NOT_SUPPORTED(BigInteger.valueOf(5206)),
        EXTENAL_OBJECT_NOT_REACHABLE(BigInteger.valueOf(6003)),
        EXTENAL_OBJECT_NOT_FOUND(BigInteger.valueOf(6005)),
        MAX_NUMBERF_OF_MEMBER_EXCEEDED(BigInteger.valueOf(6010)),
        MEMBER_TYPE_INCONSISTENT(BigInteger.valueOf(6011)),
        MGMT_SESSION_CANNOT_BE_ESTABLISHED(BigInteger.valueOf(6020)),
        MGMT_SESSION_ESTABLISHMENT__TIMEOUT(BigInteger.valueOf(6021)),
        INVALID__CMDTYPE(BigInteger.valueOf(6022)),
        INVALID_ARGUMENTS(BigInteger.valueOf(6023)),
        INSUFFICIENT_ARGUMENTS(BigInteger.valueOf(6024)),
        MGMT_CONVERSION_ERROR(BigInteger.valueOf(6025)),
        MGMT_CANCELATION_FAILURE(BigInteger.valueOf(6026)),
        ALREADY_COMPLETE(BigInteger.valueOf(6028)),
        COMMAND_NOT_CANCELLABLE(BigInteger.valueOf(6029));


        private BigInteger value;

        ResponseStatusCodes(BigInteger value) {
            this.value = value;
        }

        public static ResponseStatusCodes getEnum(BigInteger number) {
            for (OneM2M.ResponseStatusCodes code : OneM2M.ResponseStatusCodes.values()) {
                if (code.value.equals(number))
                    return code;
            }
            return null;
        }
    }

    public enum AccessControlOperations {
        CREATE(BigInteger.valueOf(1)),
        RETRIEVE(BigInteger.valueOf(2)),
        UPDATE(BigInteger.valueOf(4)),
        DELETE(BigInteger.valueOf(8)),
        DISCOVERY(BigInteger.valueOf(16)),
        NOTIFY(BigInteger.valueOf(32));

        private BigInteger value;

        AccessControlOperations(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum CmdType {
        RESET(BigInteger.valueOf(1)),
        REBOOT(BigInteger.valueOf(2)),
        UPLOAD(BigInteger.valueOf(3)),
        DOWNLOAD(BigInteger.valueOf(4)),
        SOFTWAREINSTALL(BigInteger.valueOf(5)),
        SOFTWAREUNINSTALL(BigInteger.valueOf(6)),
        SOFTWAREUPDATE(BigInteger.valueOf(7));


        private BigInteger value;

        CmdType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum BatteryStatus {
        NORMAL(BigInteger.valueOf(1)),
        CHARGING(BigInteger.valueOf(2)),
        CHARGING_COMPLETE(BigInteger.valueOf(3)),
        DAMAGED(BigInteger.valueOf(4)),
        LOW_BATTERY(BigInteger.valueOf(5)),
        NOT_INSTALLED(BigInteger.valueOf(6)),
        UNKNOWN(BigInteger.valueOf(7));


        private BigInteger value;

        BatteryStatus(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum ResourceType {
        ACCESS_CONTROL_POLICY(BigInteger.valueOf(1)),
        AE(BigInteger.valueOf(2)),
        CONTAINER(BigInteger.valueOf(3)),
        CONTENT_INSTANCE(BigInteger.valueOf(4)),
        CSE_BASE(BigInteger.valueOf(5)),
        DELIVERY(BigInteger.valueOf(6)),
        EVENT_CONFIG(BigInteger.valueOf(7)),
        EXEC_INSTANCE(BigInteger.valueOf(8)),
        GROUP(BigInteger.valueOf(9)),
        LOCATION_POLICY(BigInteger.valueOf(10)),
        M2M_SERVICE_SUBSCRIPTION_PROFILE(BigInteger.valueOf(11)),
        MGMT_CMD(BigInteger.valueOf(12)),
        MGMT_OBJ(BigInteger.valueOf(13)),
        NODE(BigInteger.valueOf(14)),
        POLLING_CHANNEL(BigInteger.valueOf(15)),
        REMOTE_CSE(BigInteger.valueOf(16)),
        REQUEST(BigInteger.valueOf(17)),
        SCHEDULE(BigInteger.valueOf(18)),
        SERVICE_SUBSCRIBED_APP_RULE(BigInteger.valueOf(19)),
        SERVICE_SUBSCRIBED_NODE(BigInteger.valueOf(20)),
        STATS_COLLECT(BigInteger.valueOf(21)),
        STATS_CONFIG(BigInteger.valueOf(22)),
        SUBSCRIPTION(BigInteger.valueOf(23)),
        ACCESS_CONTROL_POLICY_ANNC(BigInteger.valueOf(10001)),
        AE_ANNC(BigInteger.valueOf(10002)),
        CONTAINER_ANNC(BigInteger.valueOf(10003)),
        CONTENT_INSTANCE_ANNC(BigInteger.valueOf(10004)),
        GROUP_ANNC(BigInteger.valueOf(10009)),
        LOCATION_POLICY_ANNC(BigInteger.valueOf(10010)),
        MGMT_OBJ_ANNC(BigInteger.valueOf(10013)),
        NODE_ANNC(BigInteger.valueOf(10014)),
        REMOTE_CSE_ANNC(BigInteger.valueOf(10016)),
        SCHEDULE_ANNC(BigInteger.valueOf(10018));


        private BigInteger value;

        ResourceType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public static class Name {
        public enum Primitive {
            OPERATION("operation", "op"),
            TO("to", "to"),
            FROM("from", "fr"),
            REQUEST_IDENTIFIER("requestIdentifier", "rqi"),
            RESOURCE_TYPE("resourceType", "ty"),
            NAME("name", "nm"),
            CONTENT("content", "pc"),
            ORIGINATING_TIMESTAMP("originatingTimestamp", "ot"),
            REQUEST_EXPIRATION_TIMESTAMP("requestExpirationTimestamp", "rqet"),
            RESULT_EXPIRATION_TIMESTAMP("resultExpirationTimestamp", "rset"),
            OPERATION_EXECUTION_TIME("operationExecutionTime", "oet"),
            RESPONSE_TYPE("responseType", "rt"),
            RESULT_PERSISTENCE("resultPersistence", "rp"),
            RESULT_CONTENT("resultContent", "rcn"),
            EVENT_CATEGORY("eventCategory", "ec"),
            DELIVERY_AGGREGATION("deliveryAggregation", "da"),
            GROUP_REQUEST_IDENTIFIER("groupRequestIdentifier", "gid"),
            FILTER_CRITERIA("filterCriteria", "fc"),
            CREATED_BEFORE("createdBefore", "crb"),
            CREATED_AFTER("createdAfter", "cra"),
            MODIFIED_SINCE("modifiedSince", "ms"),
            UNMODIFIED_SINCE("unmodifiedSince", "us"),
            STATE_TAG_SMALLER("stateTagSmaller", "sts"),
            STATE_TAG_BIGGER("stateTagBigger", "stb"),
            EXPIRE_BEFORE("expireBefore", "exb"),
            EXPIRE_AFTER("expireAfter", "exa"),
            LABELS("labels", "lbl"),
            FILTER_CRITERIA_RESOURCE_TYPE("resourceType", "rty"),
            SIZE_ABOVE("sizeAbove", "sza"),
            SIZE_BELOW("sizeBelow", "szb"),
            CONTENT_TYPE("contentType", "cty"),
            LIMIT("limit", "lim"),
            ATTRIBUTE("attribute", "atr"),
            FILTER_USAGE("filterUsage", "fu"),
            DISCOVERY_RESULT_TYPE("discoveryResultType", "drt"),
            RESPONSE_STATUS_CODE("responseStatusCode", "rsc"),
            REQUEST_PRIMITIVE("requestPrimitive", "req"),
            RESPONSE_PRIMITIVE("responsePrimitive", "rsp");
            private String shortName;
            private String fullName;

            public String shortName() {
                return shortName;
            }

            public String fullName() {
                return fullName;
            }

            public static Primitive getEnum(String name) {
                for (Primitive p : Primitive.values()) {
                    if (p.shortName.equals(name) || p.fullName.equals(name))
                        return p;
                }
                return null;
            }

            public String toString() {
                return shortName;
            }

            Primitive(String fullName, String shortName) {
                this.shortName = shortName;
                this.fullName = fullName;
            }
        }

        public enum ResourceAttribute {
            ACCESS_CONTROL_POLICY_IDS("accessControlPolicyIDs", "acpi"),
            ANNOUNCED_ATTRIBUTE("announcedAttribute", "aa"),
            ANNOUNCE_TO("announceTo", "at"),
            CREATION_TIME("creationTime", "ct"),
            EXPIRATION_TIME("expirationTime", "et"),
            LAST_MODIFIED_TIME("lastModifiedTime", "lt"),
            PARENT_ID("parentID", "pi"),
            RESOURCE_ID("resourceID", "ri"),
            STATE_TAG("stateTag", "st"),
            RESOURCE_NAME("resourceName", "rn"),
            PRIVILEGES("privileges", "pv"),
            SELF_PRIVILEGES("selfPrivileges", "pvs"),
            APP_ID("appID", "api"),
            AE_ID("aEID", "aei"),
            APP_NAME("appName", "apn"),
            POINT_OF_ACCESS("pointOfAccess", "poa"),
            ONTOLOGY_REF("ontologyRef", "or"),
            NODE_LINK("nodeLink", "nl"),
            CREATOR("creator", "cr"),
            MAX_NR_OF_INSTANCES("maxNrOfInstances", "mni"),
            MAX_BYTE_SIZE("maxByteSize", "mbs"),
            MAX_INSTANCE_AGE("maxInstanceAge", "mia"),
            CURRENT_NR_OF_INSTANCES("currentNrOfInstances", "cni"),


            CURRENT_BYTE_SIZE("currentByteSize", "cbs"),
            LATEST("latest", "la"),
            LOCATION_ID("locationID", "li"),
            CONTENT_INFO("contentInfo", "cnf"),
            CONTENT_SIZE("contentSize", "cs"),
            CONTENT("content", "con"),
            CSE_TYPE("cseType", "cst"),
            CSE_ID("cSEID", "csi"),
            SUPPORTED_RESOURCE_TYPE("supportedResourceType", "srt"),
            NOTIFICATION_CONGESTION_POLICY("notificationCongestionPolicy", "ncp"),
            SOURCE("source", "sr"),
            TARGET("target", "tg"),
            LIFESPAN("lifespan", "ls"),
            EVENT_CAT("eventCat", "ec*"),
            DELIVERY_META_DATA("deliveryMetaData", "dmd"),
            AGGREGATED_REQUEST("aggregatedRequest", "arq"),
            EVENT_ID("eventID", "evi"),
            EVENT_TYPE("eventType", "evt"),
            EVEN_START("evenStart", "evs"),
            EVENT_END("eventEnd", "eve"),
            OPERATION_TYPE("operationType", "opt"),
            DATA_SIZE("dataSize", "ds"),
            EXEC_STATUS("execStatus", "exs"),
            EXEC_RESULT("execResult", "exr"),
            EXEC_DISABLE("execDisable", "exd"),
            EXEC_TARGET("execTarget", "ext"),
            EXEC_MODE("execMode", "exm"),
            EXEC_FREQUENCY("execFrequency", "exf"),
            EXEC_DELAY("execDelay", "exy"),
            EXEC_NUMBER("execNumber", "exn"),
            EXEC_REQ_ARGS("execReqArgs", "exra"),
            EXEC_ENABLE("execEnable", "exe"),
            MEMBER_TYPE("memberType", "mt"),
            CURRENT_NR_OF_MEMBERS("currentNrOfMembers", "cnm"),
            MAX_NR_OF_MEMBERS("maxNrOfMembers", "mnm"),
            MEMBER_ID("memberID", "mid"),
            MEMBERS_ACCESS_CONTROL_POLICY_IDS("membersAccessControlPolicyIDs", "macp"),
            MEMBER_TYPE_VALIDATED("memberTypeValidated", "mtv"),
            CONSISTENCY_STRATEGY("consistencyStrategy", "csy"),
            GROUP_NAME("groupName", "gn"),
            LOCATION_SOURCE("locationSource", "los"),
            LOCATION_UPDATE_PERIOD("locationUpdatePeriod", "lou"),
            LOCATION_TARGET_ID("locationTargetId", "lot"),
            LOCATION_SERVER("locationServer", "lor"),
            LOCATION_CONTAINER_ID("locationContainerID", "loi"),
            LOCATION_CONTAINER_NAME("locationContainerName", "lon"),
            LOCATION_STATUS("locationStatus", "lost"),
            SERVICE_ROLES("serviceRoles", "svr"),
            DESCRIPTION("description", "dc"),
            CMD_TYPE("cmdType", "cmt"),
            MGMT_DEFINITION("mgmtDefinition", "mgd"),
            OBJECT_IDS("objectIDs", "obis"),


            OBJECT_PATHS("objectPaths", "obps"),
            NODE_ID("nodeID", "ni"),
            HOSTED_CSE_LINK("hostedCSELink", "hcl"),
            CSE_BASE("cSEBase", "cb"),
            M2_M_EXT_ID("m2MExtID", "mei"),
            TRIGGER_RECIPIENT_ID("triggerRecipientID", "tri"),
            REQUEST_REACHABILITY("requestReachability", "rr"),
            ORIGINATOR("originator", "og"),
            META_INFORMATION("metaInformation", "mi"),
            REQUEST_STATUS("requestStatus", "rs"),
            OPERATION_RESULT("operationResult", "ol"),
            OPERATION("operation", "opn"),
            REQUEST_ID("requestID", "rid"),
            SCHEDULE_ELEMENT("scheduleElement", "se"),
            DEVICE_IDENTIFIER("deviceIdentifier", "di"),
            STATS_COLLECT_ID("statsCollectID", "sci"),
            COLLECTING_ENTITY_ID("collectingEntityID", "cei"),
            COLLECTED_ENTITY_ID("collectedEntityID", "cdi"),
            STATUS("status", "ss"),
            STATS_RULE_STATUS("statsRuleStatus", "srs"),
            STAT_MODEL("statModel", "sm"),
            COLLECT_PERIOD("collectPeriod", "cp"),
            EVENT_NOTIFICATION_CRITERIA("eventNotificationCriteria", "enc"),
            EXPIRATION_COUNTER("expirationCounter", "exc"),
            NOTIFICATION_URI("notificationURI", "nu"),
            NOTIFICATION_FORWARDING_URI("notificationForwardingURI", "nfu"),
            BATCH_NOTIFY("batchNotify", "bn"),
            RATE_LIMIT("rateLimit", "rl"),
            PRE_SUBSCRIPTION_NOTIFY("preSubscriptionNotify", "psn"),
            PENDING_NOTIFICATION("pendingNotification", "pn"),
            NOTIFICATION_STORAGE_PRIORITY("notificationStoragePriority", "nsp"),
            LATEST_NOTIFY("latestNotify", "ln"),
            NOTIFICATION_CONTENT_TYPE("notificationContentType", "nct"),
            NOTIFICATION_EVENT_CAT("notificationEventCat", "nec"),
            SUBSCRIBER_URI("subscriberURI", "su"),
            VERSION("version", "vr"),
            URL("URL", "url"),
            UPDATE("update", "ud"),
            UPDATE_STATUS("updateStatus", "uds"),
            INSTALL("install", "in"),
            UNINSTALL("uninstall", "un"),
            INSTALL_STATUS("installStatus", "ins"),
            ACTIVATE("activate", "act"),
            DEACTIVATE("deactivate", "dea"),
            ACTIVATE_STATUS("activateStatus", "acts"),
            MEM_AVAILABLE("memAvailable", "mma"),
            MEM_TOTAL("memTotal", "mmt"),


            AREA_NWK_TYPE("areaNwkType", "ant"),
            LIST_OF_DEVICES("listOfDevices", "ldv"),
            DEV_ID("devId", "dvd"),
            DEV_TYPE("devType", "dvt"),
            AREA_NWK_ID("areaNwkId", "awi"),
            SLEEP_INTERVAL("sleepInterval", "sli"),
            SLEEP_DURATION("sleepDuration", "sld"),
            LIST_OF_NEIGHBORS("listOfNeighbors", "lnh"),
            BATTERY_LEVEL("batteryLevel", "btl"),
            BATTERY_STATUS("batteryStatus", "bts"),
            DEVICE_LABEL("deviceLabel", "dlb"),
            MANUFACTURER("manufacturer", "man"),
            MODEL("model", "mod"),
            DEVICE_TYPE("deviceType", "dty"),
            FW_VERSION("fwVersion", "fwv"),
            SW_VERSION("swVersion", "swv"),
            HW_VERSION("hwVersion", "hwv"),
            CAPABILITY_NAME("capabilityName", "can"),
            ATTACHED("attached", "att"),
            CAPABILITY_ACTION_STATUS("capabilityActionStatus", "cas"),
            ENABLE("enable", "ena"),
            DISABLE("disable", "dis"),
            CURRENT_STATE("currentState", "cus"),
            REBOOT("reboot", "rbo"),
            FACTORY_RESET("factoryReset", "far"),
            LOG_TYPE_ID("logTypeId", "lgt"),
            LOG_DATA("logData", "lgd"),
            LOG_ACTION_STATUS("logActionStatus", "lgs"),
            LOG_START("logStart", "lga"),
            LOG_STOP("logStop", "lgo"),
            NAME("name", "nam"),
            MGMT_LINK("mgmtLink", "cmlk"),
            ORDER("order", "od"),
            DEF_EC_VALUE("defEcValue", "dev"),
            REQUEST_ORIGIN("requestOrigin", "ror"),
            REQUEST_CONTEXT("requestContext", "rct"),
            REQUEST_CONTEXT_NOTIFICATION("requestContextNotification", "rcn"),
            REQUEST_CHARACTERISTICS("requestCharacteristics", "rch"),
            APPLICABLE_EVENT_CATEGORIES("applicableEventCategories", "aecs"),
            APPLICABLE_EVENT_CATEGORY("applicableEventCategory", "aec"),
            DEFAULT_REQUEST_EXP_TIME("defaultRequestExpTime", "dqet"),
            DEFAULT_RESULT_EXP_TIME("defaultResultExpTime", "dset"),
            DEFAULT_OP_EXEC_TIME("defaultOpExecTime", "doet"),
            DEFAULT_RESP_PERSISTENCE("defaultRespPersistence", "drp"),
            DEFAULT_DEL_AGGREGATION("defaultDelAggregation", "dda"),
            LIMITS_EVENT_CATEGORY("limitsEventCategory", "lec"),
            LIMITS_REQUEST_EXP_TIME("limitsRequestExpTime", "lqet"),
            LIMITS_RESULT_EXP_TIME("limitsResultExpTime", "lset"),
            LIMITS_OP_EXEC_TIME("limitsOpExecTime", "loet"),
            LIMITS_RESP_PERSISTENCE("limitsRespPersistence", "lrp"),
            LIMITS_DEL_AGGREGATION("limitsDelAggregation", "lda"),
            TARGET_NETWORK("targetNetwork", "ttn"),


            MIN_REQ_VOLUME("minReqVolume", "mrv"),
            BACK_OFF_PARAMETERS("backOffParameters", "bop"),
            OTHER_CONDITIONS("otherConditions", "ohc"),
            MAX_BUFFER_SIZE("maxBufferSize", "mbfs"),
            STORAGE_PRIORITY("storagePriority", "sgp"),
            APPLICABLE_CRED_IDS("applicableCredIDs", "aci"),
            ALLOWED_APP_IDS("allowedAppIDs", "aai"),
            ALLOWED_AES("allowedAEs", "aae"),
            ACCESS_CONTROL_POLICY("accessControlPolicy", "acp"),
            ACCESS_CONTROL_POLICY_ANNC("accessControlPolicyAnnc", "acpA");

            private String shortName;
            private String fullName;

            ResourceAttribute(String fullName, String shortName) {
                this.shortName = shortName;
                this.fullName = fullName;
            }

            public static String shortName(String name) {
                for (OneM2M.Name.ResourceAttribute attr : OneM2M.Name.ResourceAttribute.values()) {
                    if (attr.fullName.equals(name))
                        return attr.shortName;
                }
                return name;
            }

            public String shortName() {
                return shortName;
            }

            public String fullName() {
                return fullName;
            }

            public String toString() {
                return shortName;
            }

        }

        public enum ComplexType {
            CREATED_BEFORE("createdBefore", "crb"),
            CREATED_AFTER("createdAfter", "cra"),
            MODIFIED_SINCE("modifiedSince", "ms"),
            UNMODIFIED_SINCE("unmodifiedSince", "us"),
            STATE_TAG_SMALLER("stateTagSmaller", "sts"),
            STATE_TAG_BIGGER("stateTagBigger", "stb"),
            EXPIRE_BEFORE("expireBefore", "exb"),
            EXPIRE_AFTER("expireAfter", "exa"),
            LABELS("labels", "lbl"),
            RESOURCE_TYPE("resourceType", "rty"),
            SIZE_ABOVE("sizeAbove", "sza"),
            SIZE_BELOW("sizeBelow", "szb"),
            CONTENT_TYPE("contentType", "cty"),
            LIMIT("limit", "lim"),
            ATTRIBUTE("attribute", "atr"),
            RESOURCE_STATUS("resourceStatus", "rss"),
            OPERATION_MONITOR("operationMonitor", "om"),
            FILTER_USAGE("filterUsage", "fu"),
            EVENT_CAT_TYPE("eventCatType", "ect"),
            EVENT_CAT_NO("eventCatNo", "ecn"),
            NUMBER("number", "num"),
            DURATION("duration", "dur"),
            NOTIFICATION("notification", "sgn"),
            NOTIFICATION_EVENT("notificationEvent", "nev"),
            VERIFICATION_REQUEST("verificationRequest", "vrq"),
            SUBSCRIPTION_DELETION("subscriptionDeletion", "sud"),
            SUBSCRIPTION_REFERENCE("subscriptionReference", "sur"),
            CREATOR("creator", "cr*"),
            NOTIFICATION_FORWARDING_URI("notificationForwardingURI", "nfu*"),
            OPERATION("operation", "opr"),
            ORIGINATOR("originator", "org"),
            ACCESS_ID("accessId", "aci"),
            MS_ISDN("mSISDN", "msd"),
            ACTION("action", "acn"),
            STATUS("status", "sus"),
            CHILD_RESOURCE("childResource", "ch"),
            AGGREGATED_NOTIFICATION("aggregatedNotification", "agn"),
            AGGREGATED_RESPONSE("aggregatedResponse", "agr");

            private String shortName;
            private String fullName;

            ComplexType(String fullName, String shortName) {
                this.shortName = shortName;
                this.fullName = fullName;
            }

            public String toString() {
                return shortName;
            }

            public String shortName() {
                return shortName;
            }

            public String fullName() {
                return fullName;
            }
        }

        public enum ResourceType {
            AE("aE", "ae"),
            AE_ANNC("aEAnnc", "aeA"),
            CONTAINER("container", "cnt"),
            CONTAINER_ANNC("containerAnnc", "cntA"),
            CONTENT_INSTANCE("contentInstance", "cin"),
            CONTENT_INSTANCE_ANNC("contentInstanceAnnc", "cinA"),
            CSE_BASE("cSEBase", "csb"),
            DELIVERY("delivery", "dlv"),
            EVENT_CONFIG("eventConfig", "evcg"),
            EXEC_INSTANCE("execInstance", "exin"),
            FAN_OUT_POINT("fanOutPoint", "fopt"),
            GROUP("group", "grp"),
            GROUP_ANNC("groupAnnc", "grpA"),
            LOCATION_POLICY("locationPolicy", "lcp"),
            LOCATION_POLICY_ANNC("locationPolicyAnnc", "lcpA"),
            M2M_SERVICE_SUBSCRIPTION_PROFILE("m2mServiceSubscriptionProfile", "mssp"),
            MGMT_CMD("mgmtCmd", "mgc"),
            MGMT_OBJ("mgmtObj", "mgo"),
            MGMT_OBJ_ANNC("mgmtObjAnnc", "mgoA"),
            NODE("node", "nod"),
            NODE_ANNC("nodeAnnc", "nodA"),
            POLLING_CHANNEL("pollingChannel", "pch"),
            POLLING_CHANNEL_URI("pollingChannelURI", "pcu"),
            REMOTE_CSE("remoteCSE", "csr"),
            REMOTE_CSE_ANNC("remoteCSEAnnc", "csrA"),
            REQUEST("request", "req"),
            SCHEDULE("schedule", "sch"),
            SCHEDULE_ANNC("scheduleAnnc", "schA"),
            SERVICE_SUBSCRIBED_APP_RULE("serviceSubscribedAppRule", "asar"),
            SERVICE_SUBSCRIBED_NODE("serviceSubscribedNode", "svsn"),
            STATS_COLLECT("statsCollect", "stcl"),
            STATS_CONFIG("statsConfig", "stcg"),
            SUBSCRIPTION("subscription", "sub"),
            FIRMWARE("firmware", "fwr"),
            SOFTWARE("software", "swr"),
            MEMORY("memory", "mem"),
            AREA_NWK_INFO("areaNwkInfo", "ani"),
            AREA_NWK_DEVICE_INFO("areaNwkDeviceInfo", "andi"),
            BATTERY("battery", "bat"),
            DEVICE_INFO("deviceInfo", "dvi"),
            DEVICE_CAPABILITY("deviceCapability", "dvc"),
            REBOOT("reboot", "rbt"),
            EVENT_LOG("eventLog", "evl"),
            CMDH_POLICY("cmdhPolicy", "cmp"),
            ACTIVE_CMDH_POLICY("activeCmdhPolicy", "acmp"),
            CMDH_DEFAULTS("cmdhDefaults", "cmdf"),
            CMDH_DEF_EC_VALUE("cmdhDefEcValue", "cmdv"),
            CMDH_EC_DEF_PARAM_VALUES("cmdhEcDefParamValues", "cmpv"),
            CMDH_LIMITS("cmdhLimits", "cml"),
            CMDH_NETWORK_ACCESS_RULES("cmdhNetworkAccessRules", "cmnr"),
            CMDH_NW_ACCESS_RULE("cmdhNwAccessRule", "cmwr"),
            CMDH_BUFFER("cmdhBuffer", "cmbf");

            private String shortName;
            private String fullName;

            ResourceType(String fullName, String shortName) {
                this.shortName = shortName;
                this.fullName = fullName;
            }

            public String shortName() {
                return shortName;
            }

            public String fullName() {
                return fullName;
            }

            public String toString() {
                return shortName;
            }
        }
    }

    public static class CoAP {
        public static String NAME = "coap";

        public enum Option {
            ONEM2M_FR(256),
            ONEM2M_RQI(257),
            ONEM2M_NM(258),
            ONEM2M_OT(259),
            ONEM2M_RQET(260),
            ONEM2M_RSET(261),
            ONEM2M_OET(262),
            ONEM2M_RTURI(263),
            ONEM2M_EC(264),
            ONEM2M_RSC(265),
            ONEM2M_GID(266);

            private int number;

            public static Option getEnum(int number) {
                for (OneM2M.CoAP.Option option : OneM2M.CoAP.Option.values()) {
                    if (option.number == number)
                        return option;
                }
                return null;
            }

            public int value() {
                return number;
            }

            Option(int number) {
                this.number = number;
            }
        }
    }

    public static class Http {
        public static String NAME = "http";

        public static class Header {
            public static final String X_M2M_ORIGIN = "X-M2M-Origin";
            public static final String X_M2M_RI = "X-M2M-RI";
            public static final String X_M2M_NM = "X-M2M-NM";
            public static final String X_M2M_GID = "X-M2M-GID";
            public static final String X_M2M_RTU = "X-M2M-RTU";
            public static final String X_M2M_OT = "X-M2M-OT";
            public static final String X_M2M_RST = "X-M2M-RST";
            public static final String X_M2M_RET = "X-M2M-RET";
            public static final String X_M2M_OET = "X-M2M-OET";
            public static final String X_M2M_EC = "X-M2M-EC";
            public static final String X_M2M_RSC = "X-M2M-RSC";
        }
    }
}
