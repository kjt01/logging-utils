# logging-utils
This is a library to simplify the logging using annotation.

```
    <dependency>
        <groupId>com.kjt</groupId>
        <artifactId>logging-utils</artifactId>
        <version>1.0</version>
    </dependency>
```

## @LogRequestBody
This annotation will log the annotated @RequestBody in the arguments

##### @LogRequestBody(prettify = false)
```
    // Logging is not prettified
    @PostMapping("/validate")
    @LogRequestBody
    public List<Object> validateQuestion(@RequestBody List<QuestionDto> questionDtos) {
        return questionService.validateQuestion(questionDtos);
    }
```
    
```
    [e4859023-2b2e-4c64-ac65-b0b6348779f9] INFO  com.kjt.logging.LoggingAspect.writeLogs - Logging requestBody: [{"id":"b7193a54-34b6-11ed-b80d-701003dd4ce5","question":"Does Steam and Fried Dumpling come w. Chinese cabbage}
```


##### @LogRequestBody(prettify = true)
```
    // Logging is not prettified
    @PostMapping("/validate")
    @LogRequestBody
    public List<Object> validateQuestion(@RequestBody List<QuestionDto> questionDtos) {
        return questionService.validateQuestion(questionDtos);
    }
```
    
```
   2023-01-12 23:02:36.774 [http-nio-8144-exec-2] [34d652ce-1c5f-4a19-9b02-50ca1a1feec1] INFO  com.kjt.logging.LoggingAspect.writeLogs - Logging requestBody: [ {
     "id" : "b7193a54-34b6-11ed-b80d-701003dd4ce5",
     "question" : "Does Steam and Fried Dumpling come w. Chinese cabbage, scallion, and ground pork?",
     "tag" : "dumplings",
     "referenceKeys" : [ "94c62b26-28eb-4485-8b75-1d86f0e543e9", "dc6e5543-4038-48f7-a6c5-df7ad0493ae1" ],
     "ruleType" : "DISH_DESCRIPTION",
     "template" : "${meat} Mix w. ${veg} (Comes w. ${oz} Dumpling Sauce on The Side)",
     "referenceQuestions" : [ ],
     "choices" : [ {
       "placeholder" : "dish",
       "choiceGroup" : "Dish",
       "options" : [ {
         "isDefault" : true,
         "isSelected" : false,
         "isRemovable" : false,
         "label" : "dumplings",
         "value" : "dumplings",
         "isCountable" : false,
         "count" : 0,
         "name" : "dumplings",
         "dishSizeQuantities" : [ ]
       } ],

```

## Correlation ID
This will generate a unique UUID, if not present, to response header *(X-Correlation-Id)* which can be received or passed to downstream/upstream services. 

##### Adding Correlation ID to logs
Add `[%X{correlationId}]` in log pattern for logback config.
```$xslt
<property name="pattern" value="%magenta(%d{yyyy-MM-dd HH:mm:ss.SSS}) %green([%thread]) [%X{correlationId}] %highlight(%-5level) %yellow(%logger{36}.%M) - %msg%n" />
```

```$xslt
2023-02-17 19:28:56.075 [http-nio-8144-exec-5] [56c9de9a-9e7e-42ad-a459-6be7506f4656] INFO  com.kjt.rop.config.Slf4jMDCFilter.logRequest - Request path: GET /restaurantonboardingservice/e04120fa-44e1-4044-bb27-59267149f8ba?type=RESTAURANT_INFO&name=RESTAURANT_DETAILS
2023-02-17 19:28:56.081 [http-nio-8144-exec-5] [56c9de9a-9e7e-42ad-a459-6be7506f4656] INFO  c.k.rop.controllers.ScreenController.getInfo - Get details: RESTAURANT_DETAILS | rid: e04120fa-44e1-4044-bb27-59267149f8ba
```


