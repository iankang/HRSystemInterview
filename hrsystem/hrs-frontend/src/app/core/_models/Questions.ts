export class Questions {
    createdAt: string
    updatedAt: string
    id: number
    questionAsked: QuestionAsked
    answerGiven: any
    timeTaken: number
  }
  
  export class QuestionAsked {
    id: number
    topicQuestion: string
    answers: Answer[]
  }
  
  export class Answer {
    id: number
    answer: string
    isCorrect: boolean
  }