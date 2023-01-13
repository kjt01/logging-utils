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




