# logging-utils
This is a library to simplify the logging using annotation.

## @LogRequestBody
This annotation will log the annotated @RequestBody in the arguments

    @PostMapping("/validate")
    @LogRequestBody
    public List<Object> validateQuestion(@RequestBody List<QuestionDto> questionDtos) {
        return questionService.validateQuestion(questionDtos);
    }
