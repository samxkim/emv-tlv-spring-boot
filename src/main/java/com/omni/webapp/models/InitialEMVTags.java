package com.omni.webapp.models;

public class InitialEMVTags {
    public static final Tag UNIVERSAL_TAG_FOR_OID = new Tag("06", "Object Identifier (OID): Universal tag for OID");
    public static final Tag COUNTRY_CODE = new Tag("41", "Country Code: Country code (encoding specified in ISO 3166-1) and optional national data");
    public static final Tag ISSUER_IDENTIFICATION_NUMBER = new Tag("42", "Issuer Identification Number (IIN): The number that identifies the major industry and the card issuer and that forms the first part of the Primary Account Number (PAN)");
    public static final Tag AID_CARD = new Tag("4f", "Application Identifier (AID) - card: Identifies the application as described in ISO/IEC 7816-5");
    public static final Tag APPLICATION_LABEL = new Tag("50", "Application Label: Mnemonic associated with the AID according to ISO/IEC 7816-5");
    public static final Tag PATH = new Tag("51", "File reference data element: ISO-7816 Path");
    public static final Tag COMMAND_APDU = new Tag("52", "Command APDU");
    public static final Tag DISCRETIONARY_DATA_OR_TEMPLATE = new Tag("53", "Discretionary data (or template)");
    public static final Tag APPLICATION_TEMPLATE = new Tag("61", "Application Template: Contains one or more data objects relevant to an application directory entry according to ISO/IEC 7816-5");
    public static final Tag FCI_TEMPLATE = new Tag("6f", "File Control Information (FCI) Template: Set of file control parameters and file management data (according to ISO/IEC 7816-4)");
    public static final Tag DD_TEMPLATE = new Tag("73", "Directory Discretionary Template: Issuer discretionary part of the directory according to ISO/IEC 7816-5");
    public static final Tag DEDICATED_FILE_NAME = new Tag("84", "Dedicated File (DF) Name: Identifies the name of the DF as described in ISO/IEC 7816-4");
    public static final Tag SFI = new Tag("88", "Short File Identifier (SFI): Identifies the SFI to be used in the commands related to a given AEF or DDF. The SFI data object is a binary field with the three high order bits set to zero");
    public static final Tag FCI_PROPRIETARY_TEMPLATE = new Tag("a5", "File Control Information (FCI) Proprietary Template: Identifies the data object proprietary to this specification in the FCI template according to ISO/IEC 7816-4");
    public static final Tag ISSUER_URL = new Tag("5f50", "Issuer URL: The URL provides the location of the Issuer’s Library Server on the Internet");
    public static final Tag TRACK_2_EQV_DATA = new Tag("57", "Track 2 Equivalent Data: Contains the data elements of track 2 according to ISO/IEC 7813, excluding start sentinel, end sentinel, and Longitudinal Redundancy Check (LRC)");
    public static final Tag PAN = new Tag("5a", "Application Primary Account Number (PAN): Valid cardholder account number");
    public static final Tag RECORD_TEMPLATE = new Tag("70", "Record Template (EMV Proprietary): Template proprietary to the EMV specification");
    public static final Tag ISSUER_SCRIPT_TEMPLATE_1 = new Tag("71", "Issuer Script Template 1: Contains proprietary issuer data for transmission to the ICC before the second GENERATE AC command");
    public static final Tag ISSUER_SCRIPT_TEMPLATE_2 = new Tag("72", "Issuer Script Template 2: Contains proprietary issuer data for transmission to the ICC after the second GENERATE AC command");
    public static final Tag RESPONSE_MESSAGE_TEMPLATE_2 = new Tag("77", "Response Message Template Format 2: Contains the data objects (with tags and lengths) returned by the ICC in response to a command");
    public static final Tag RESPONSE_MESSAGE_TEMPLATE_1 = new Tag("80", "Response Message Template Format 1: Contains the data objects (without tags and lengths) returned by the ICC in response to a command");
    public static final Tag AMOUNT_AUTHORISED_BINARY = new Tag("81", "Amount, Authorised (Binary): Authorised amount of the transaction (excluding adjustments)");
    public static final Tag APPLICATION_INTERCHANGE_PROFILE = new Tag("82", "Application Interchange Profile: Indicates the capabilities of the card to support specific functions in the application");
    public static final Tag COMMAND_TEMPLATE = new Tag("83", "Command Template: Identifies the data field of a command message");
    public static final Tag ISSUER_SCRIPT_COMMAND = new Tag("86", "Issuer Script Command: Contains a command for transmission to the ICC");
    public static final Tag APPLICATION_PRIORITY_INDICATOR = new Tag("87", "Application Priority Indicator: Indicates the priority of a given application or group of applications in a directory");
    public static final Tag AUTHORISATION_CODE = new Tag("89", "Authorisation Code: Value generated by the authorisation authority for an approved transaction");
    public static final Tag AUTHORISATION_RESPONSE_CODE = new Tag("8a", "Authorisation Response Code: Code that defines the disposition of a message");
    public static final Tag CDOL1 = new Tag("8c", "Card Risk Management Data Object List 1 (CDOL1): List of data objects (tag and length) to be passed to the ICC in the first GENERATE AC command");
    public static final Tag CDOL2 = new Tag("8d", "Card Risk Management Data Object List 2 (CDOL2): List of data objects (tag and length) to be passed to the ICC in the second GENERATE AC command");
    public static final Tag CVM_LIST = new Tag("8e", "Cardholder Verification Method (CVM) List: Identifies a method of verification of the cardholder supported by the application");
    public static final Tag CA_PUBLIC_KEY_INDEX_CARD = new Tag("8f", "Certification Authority Public Key Index - card: Identifies the certification authority’s public key in conjunction with the RID");
    public static final Tag ISSUER_PUBLIC_KEY_CERT = new Tag("90", "Issuer Public Key Certificate: Issuer public key certified by a certification authority");
    public static final Tag ISSUER_AUTHENTICATION_DATA = new Tag("91", "Issuer Authentication Data: Data sent to the ICC for online issuer authentication");
    public static final Tag ISSUER_PUBLIC_KEY_REMAINDER = new Tag("92", "Issuer Public Key Remainder: Remaining digits of the Issuer Public Key Modulus");
    public static final Tag SIGNED_STATIC_APP_DATA = new Tag("93", "Signed Static Application Data: Digital signature on critical application parameters for SDA");
    public static final Tag APPLICATION_FILE_LOCATOR = new Tag("94", "Application File Locator (AFL): Indicates the location (SFI, range of records) of the AEFs related to a given application");
    public static final Tag TERMINAL_VERIFICATION_RESULTS = new Tag("95", "Terminal Verification Results (TVR): Status of the different functions as seen from the terminal");
    public static final Tag TDOL = new Tag("97", "Transaction Certificate Data Object List (TDOL): List of data objects (tag and length) to be used by the terminal in generating the TC Hash Value");
    public static final Tag TC_HASH_VALUE = new Tag("98", "Transaction Certificate (TC) Hash Value: Result of a hash function specified in Book 2, Annex B3.1");
    public static final Tag TRANSACTION_PIN_DATA = new Tag("99", "Transaction Personal Identification Number (PIN) Data: Data entered by the cardholder for the purpose of the PIN verification");
    public static final Tag TRANSACTION_DATE = new Tag("9a", "Transaction Date: Local date that the transaction was authorised");
    public static final Tag TRANSACTION_STATUS_INFORMATION = new Tag("9b", "Transaction Status Information: Indicates the functions performed in a transaction");
    public static final Tag TRANSACTION_TYPE = new Tag("9c", "Transaction Type: Indicates the type of financial transaction, represented by the first two digits of ISO 8583:1987 Processing Code");
    public static final Tag DDF_NAME = new Tag("9d", "Directory Definition File (DDF) Name: Identifies the name of a DF associated with a directory");
    public static final Tag CARDHOLDER_NAME = new Tag("5f20", "Cardholder Name: Indicates cardholder name according to ISO 7813");
    public static final Tag APP_EXPIRATION_DATE = new Tag("5f24", "Application Expiration Date: Date after which application expires");
    public static final Tag APP_EFFECTIVE_DATE = new Tag("5f25", "Application Effective Date: Date from which the application may be used");
    public static final Tag ISSUER_COUNTRY_CODE = new Tag("5f28", "Issuer Country Code: Indicates the country of the issuer according to ISO 3166");
    public static final Tag TRANSACTION_CURRENCY_CODE = new Tag("5f2a", "Transaction Currency Code: Indicates the currency code of the transaction according to ISO 4217");
    public static final Tag LANGUAGE_PREFERENCE = new Tag("5f2d", "Language Preference: 1–4 languages stored in order of preference, each represented by 2 alphabetical characters according to ISO 639");
    public static final Tag SERVICE_CODE = new Tag("5f30", "Service Code: Service code as defined in ISO/IEC 7813 for track 1 and track 2");
    public static final Tag PAN_SEQUENCE_NUMBER = new Tag("5f34", "Application Primary Account Number (PAN) Sequence Number: Identifies and differentiates cards with the same PAN");
    public static final Tag TRANSACTION_CURRENCY_EXP = new Tag("5f36", "Transaction Currency Exponent: Indicates the implied position of the decimal point from the right of the transaction amount represented according to ISO 4217");
    public static final Tag IBAN = new Tag("5f53", "International Bank Account Number (IBAN): Uniquely identifies the account of a customer at a financial institution as defined in ISO 13616");
    public static final Tag BANK_IDENTIFIER_CODE = new Tag("5f54", "Bank Identifier Code (BIC): Uniquely identifies a bank as defined in ISO 9362");
    public static final Tag ISSUER_COUNTRY_CODE_ALPHA2 = new Tag("5f55", "Issuer Country Code (alpha2 format): Indicates the country of the issuer as defined in ISO 3166 (using a 2 character alphabetic code)");
    public static final Tag ISSUER_COUNTRY_CODE_ALPHA3 = new Tag("5f56", "Issuer Country Code (alpha3 format): Indicates the country of the issuer as defined in ISO 3166 (using a 3 character alphabetic code)");
    public static final Tag ACQUIRER_IDENTIFIER = new Tag("9f01", "Acquirer Identifier: Uniquely identifies the acquirer within each payment system");
    public static final Tag AMOUNT_AUTHORISED_NUMERIC = new Tag("9f02", "Amount, Authorised (Numeric): Authorised amount of the transaction (excluding adjustments)");
    public static final Tag AMOUNT_OTHER_NUMERIC = new Tag("9f03", "Amount, Other (Numeric): Secondary amount associated with the transaction representing a cashback amount");
    public static final Tag AMOUNT_OTHER_BINARY = new Tag("9f04", "Amount, Other (Binary): Secondary amount associated with the transaction representing a cashback amount");
    public static final Tag APP_DISCRETIONARY_DATA = new Tag("9f05", "Application Discretionary Data: Issuer or payment system specified data relating to the application");
    public static final Tag AID_TERMINAL = new Tag("9f06", "Application Identifier (AID) - terminal: Identifies the application as described in ISO/IEC 7816-5");
    public static final Tag APP_USAGE_CONTROL = new Tag("9f07", "Application Usage Control: Indicates issuer’s specified restrictions on the geographic usage and services allowed for the application");
    public static final Tag APP_VERSION_NUMBER_CARD = new Tag("9f08", "Application Version Number - card: Version number assigned by the payment system for the application");
    public static final Tag APP_VERSION_NUMBER_TERMINAL = new Tag("9f09", "Application Version Number - terminal: Version number assigned by the payment system for the application");
    public static final Tag CARDHOLDER_NAME_EXTENDED = new Tag("9f0b", "Cardholder Name Extended: Indicates the whole cardholder name when greater than 26 characters using the same coding convention as in ISO 7813");
    public static final Tag ISSUER_ACTION_CODE_DEFAULT = new Tag("9f0d", "Issuer Action Code - Default: Specifies the issuer’s conditions that cause a transaction to be rejected if it might have been approved online, but the terminal is unable to process the transaction online");
    public static final Tag ISSUER_ACTION_CODE_DENIAL = new Tag("9f0e", "Issuer Action Code - Denial: Specifies the issuer’s conditions that cause the denial of a transaction without attempt to go online");
    public static final Tag ISSUER_ACTION_CODE_ONLINE = new Tag("9f0f", "Issuer Action Code - Online: Specifies the issuer’s conditions that cause a transaction to be transmitted online");
    public static final Tag ISSUER_APPLICATION_DATA = new Tag("9f10", "Issuer Application Data: Contains proprietary application data for transmission to the issuer in an online transaction");
    public static final Tag ISSUER_CODE_TABLE_INDEX = new Tag("9f11", "Issuer Code Table Index: Indicates the code table according to ISO/IEC 8859 for displaying the Application Preferred Name");
    public static final Tag APP_PREFERRED_NAME = new Tag("9f12", "Application Preferred Name: Preferred mnemonic associated with the AID");
    public static final Tag LAST_ONLINE_ATC_REGISTER = new Tag("9f13", "Last Online Application Transaction Counter (ATC) Register: ATC value of the last transaction that went online");
    public static final Tag LOWER_CONSEC_OFFLINE_LIMIT = new Tag("9f14", "Lower Consecutive Offline Limit: Issuer-specified preference for the maximum number of consecutive offline transactions for this ICC application allowed in a terminal with online capability");
    public static final Tag MERCHANT_CATEGORY_CODE = new Tag("9f15", "Merchant Category Code: Classifies the type of business being done by the merchant, represented according to ISO 8583:1993 for Card Acceptor Business Code");
    public static final Tag MERCHANT_IDENTIFIER = new Tag("9f16", "Merchant Identifier: When concatenated with the Acquirer Identifier, uniquely identifies a given merchant");
    public static final Tag PIN_TRY_COUNTER = new Tag("9f17", "Personal Identification Number (PIN) Try Counter: Number of PIN tries remaining");
    public static final Tag ISSUER_SCRIPT_IDENTIFIER = new Tag("9f18", "Issuer Script Identifier: Identification of the Issuer Script");
    public static final Tag TERMINAL_COUNTRY_CODE = new Tag("9f1a", "Terminal Country Code: Indicates the country of the terminal, represented according to ISO 3166");
    public static final Tag TERMINAL_FLOOR_LIMIT = new Tag("9f1b", "Terminal Floor Limit: Indicates the floor limit in the terminal in conjunction with the AID");
    public static final Tag TERMINAL_IDENTIFICATION = new Tag("9f1c", "Terminal Identification: Designates the unique location of a terminal at a merchant");
    public static final Tag TERMINAL_RISK_MANAGEMENT_DATA = new Tag("9f1d", "Terminal Risk Management Data: Application-specific value used by the card for risk management purposes");
    public static final Tag INTERFACE_DEVICE_SERIAL_NUMBER = new Tag("9f1e", "Interface Device (IFD) Serial Number: Unique and permanent serial number assigned to the IFD by the manufacturer");
    public static final Tag TRACK1_DISCRETIONARY_DATA = new Tag("9f1f", "[Magnetic Stripe] Track 1 Discretionary Data: Discretionary part of track 1 according to ISO/IEC 7813");
    public static final Tag TRACK2_DISCRETIONARY_DATA = new Tag("9f20", "[Magnetic Stripe] Track 2 Discretionary Data: Discretionary part of track 2 according to ISO/IEC 7813");
    public static final Tag TRANSACTION_TIME = new Tag("9f21", "Transaction Time (HHMMSS): Local time that the transaction was authorised");
    public static final Tag CA_PUBLIC_KEY_INDEX_TERMINAL = new Tag("9f22", "Certification Authority Public Key Index - Terminal: Identifies the certification authority’s public key in conjunction with the RID");
    public static final Tag UPPER_CONSEC_OFFLINE_LIMIT = new Tag("9f23", "Upper Consecutive Offline Limit: Issuer-specified preference for the maximum number of consecutive offline transactions for this ICC application allowed in a terminal without online capability");
    public static final Tag APP_CRYPTOGRAM = new Tag("9f26", "Application Cryptogram: Cryptogram returned by the ICC in response of the GENERATE AC command");
    public static final Tag CRYPTOGRAM_INFORMATION_DATA = new Tag("9f27", "Cryptogram Information Data: Indicates the type of cryptogram and the actions to be performed by the terminal");
    public static final Tag ICC_PIN_ENCIPHERMENT_PUBLIC_KEY_CERT = new Tag("9f2d", "ICC PIN Encipherment Public Key Certificate: ICC PIN Encipherment Public Key certified by the issuer");
    public static final Tag ICC_PIN_ENCIPHERMENT_PUBLIC_KEY_EXP = new Tag("9f2e", "ICC PIN Encipherment Public Key Exponent: ICC PIN Encipherment Public Key Exponent used for PIN encipherment");
    public static final Tag ICC_PIN_ENCIPHERMENT_PUBLIC_KEY_REM = new Tag("9f2f", "ICC PIN Encipherment Public Key Remainder: Remaining digits of the ICC PIN Encipherment Public Key Modulus");
    public static final Tag ISSUER_PUBLIC_KEY_EXP = new Tag("9f32", "Issuer Public Key Exponent: Issuer public key exponent used for the verification of the Signed Static Application Data and the ICC Public Key Certificate");
    public static final Tag TERMINAL_CAPABILITIES = new Tag("9f33", "Terminal Capabilities: Indicates the card data input, CVM, and security capabilities of the terminal");
    public static final Tag CVM_RESULTS = new Tag("9f34", "Cardholder Verification (CVM) Results: Indicates the results of the last CVM performed");
    public static final Tag TERMINAL_TYPE = new Tag("9f35", "Terminal Type: Indicates the environment of the terminal, its communications capability, and its operational control");
    public static final Tag APP_TRANSACTION_COUNTER = new Tag("9f36", "Application Transaction Counter (ATC): Counter maintained by the application in the ICC (incrementing the ATC is managed by the ICC)");
    public static final Tag UNPREDICTABLE_NUMBER = new Tag("9f37", "Unpredictable Number: Value to provide variability and uniqueness to the generation of a cryptogram");
    public static final Tag PDOL = new Tag("9f38", "Processing Options Data Object List (PDOL): Contains a list of terminal resident data objects (tags and lengths) needed by the ICC in processing the GET PROCESSING OPTIONS command");
    public static final Tag POINT_OF_SERVICE_ENTRY_MODE = new Tag("9f39", "Point-of-Service (POS) Entry Mode: Indicates the method by which the PAN was entered, according to the first two digits of the ISO 8583:1987 POS Entry Mode");
    public static final Tag AMOUNT_REFERENCE_CURRENCY = new Tag("9f3a", "Amount, Reference Currency: Authorised amount expressed in the reference currency");
    public static final Tag APP_REFERENCE_CURRENCY = new Tag("9f3b", "Application Reference Currency: 1–4 currency codes used between the terminal and the ICC when the Transaction Currency Code is different from the Application Currency Code; each code is 3 digits according to ISO 4217");
    public static final Tag TRANSACTION_REFERENCE_CURRENCY_CODE = new Tag("9f3c", "Transaction Reference Currency Code: Code defining the common currency used by the terminal in case the Transaction Currency Code is different from the Application Currency Code");
    public static final Tag TRANSACTION_REFERENCE_CURRENCY_EXP = new Tag("9f3d", "Transaction Reference Currency Exponent: Indicates the implied position of the decimal point from the right of the transaction amount, with the Transaction Reference Currency Code represented according to ISO 4217");
    public static final Tag ADDITIONAL_TERMINAL_CAPABILITIES = new Tag("9f40", "Additional Terminal Capabilities: Indicates the data input and output capabilities of the terminal");
    public static final Tag TRANSACTION_SEQUENCE_COUNTER = new Tag("9f41", "Transaction Sequence Counter: Counter maintained by the terminal that is incremented by one for each transaction");
    public static final Tag APPLICATION_CURRENCY_CODE = new Tag("9f42", "Application Currency Code: Indicates the currency in which the account is managed according to ISO 4217");
    public static final Tag APP_REFERENCE_CURRECY_EXPONENT = new Tag("9f43", "Application Reference Currency Exponent: Indicates the implied position of the decimal point from the right of the amount, for each of the 1–4 reference currencies represented according to ISO 4217");
    public static final Tag APP_CURRENCY_EXPONENT = new Tag("9f44", "Application Currency Exponent: Indicates the implied position of the decimal point from the right of the amount represented according to ISO 4217");
    public static final Tag DATA_AUTHENTICATION_CODE = new Tag("9f45", "Data Authentication Code: An issuer assigned value that is retained by the terminal during the verification process of the Signed Static Application Data");
    public static final Tag ICC_PUBLIC_KEY_CERT = new Tag("9f46", "ICC Public Key Certificate: ICC Public Key certified by the issuer");
    public static final Tag ICC_PUBLIC_KEY_EXP = new Tag("9f47", "ICC Public Key Exponent: ICC Public Key Exponent used for the verification of the Signed Dynamic Application Data");
    public static final Tag ICC_PUBLIC_KEY_REMAINDER = new Tag("9f48", "ICC Public Key Remainder: Remaining digits of the ICC Public Key Modulus");
    public static final Tag DDOL = new Tag("9f49", "Dynamic Data Authentication Data Object List (DDOL): List of data objects (tag and length) to be passed to the ICC in the INTERNAL AUTHENTICATE command");
    public static final Tag SDA_TAG_LIST = new Tag("9f4a", "Static Data Authentication Tag List: List of tags of primitive data objects defined in this specification whose value fields are to be included in the Signed Static or Dynamic Application Data");
    public static final Tag SIGNED_DYNAMIC_APPLICATION_DATA = new Tag("9f4b", "Signed Dynamic Application Data: Digital signature on critical application parameters for DDA or CDA");
    public static final Tag ICC_DYNAMIC_NUMBER = new Tag("9f4c", "ICC Dynamic Number: Time-variant number generated by the ICC, to be captured by the terminal");
    public static final Tag LOG_ENTRY = new Tag("9f4d", "Log Entry: Provides the SFI of the Transaction Log file and its number of records");
    public static final Tag MERCHANT_NAME_AND_LOCATION = new Tag("9f4e", "Merchant Name and Location: Indicates the name and location of the merchant");
    public static final Tag LOG_FORMAT = new Tag("9f4f", "Log Format: List (in tag and length format) of data objects representing the logged data elements that are passed to the terminal when a transaction log record is read");
    public static final Tag FCI_ISSUER_DISCRETIONARY_DATA = new Tag("bf0c", "File Control Information (FCI) Issuer Discretionary Data: Issuer discretionary part of the FCI (e.g. O/S Manufacturer proprietary data)");
    public static final Tag TRACK1_DATA = new Tag("56", "Track 1 Data: Track 1 Data contains the data objects of the track 1 according to [ISO/IEC 7813] Structure B, excluding start sentinel, end sentinel and LRC.");
    public static final Tag TERMINAL_TRANSACTION_QUALIFIERS = new Tag("9f66", "Terminal Transaction Qualifiers: Provided by the reader in the GPO command and used by the card to determine processing choices based on reader functionality");
    public static final Tag TRACK2_DATA = new Tag("9f6b", "Track 2 Data: Track 2 Data contains the data objects of the track 2 according to [ISO/IEC 7813] Structure B, excluding start sentinel, end sentinel and LRC.");
    public static final Tag VLP_ISSUER_AUTHORISATION_CODE = new Tag("9f6e", "Visa Low-Value Payment (VLP) Issuer Authorisation Code");
    public static final Tag EXTENDED_SELECTION = new Tag("9f29", "Indicates the card's preference for the kernel on which the contactless application can be processed");
    public static final Tag KERNEL_IDENTIFIER = new Tag("9f2a", "The value to be appended to the ADF Name in the data field of the SELECT command, if the Extended Selection Support flag is present and set to 1");
}
