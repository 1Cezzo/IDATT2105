<template>
  <button class="logout-button" @click="logout">Logout</button>
  <div class="calculator-container" @keydown="handleKeyPress" tabindex="0">
    <div id="calculator" class="grid">
      <div class="grid-square" @click="handleButtonClick('7')">7</div>
      <div class="grid-square" @click="handleButtonClick('8')">8</div>
      <div class="grid-square" @click="handleButtonClick('9')">9</div>
      <div class="grid-square" @click="handleButtonClick('-')">-</div>
      <div class="grid-square" @click="handleButtonClick('4')">4</div>
      <div class="grid-square" @click="handleButtonClick('5')">5</div>
      <div class="grid-square" @click="handleButtonClick('6')">6</div>
      <div class="grid-square" @click="handleButtonClick('+')">+</div>
      <div class="grid-square" @click="handleButtonClick('1')">1</div>
      <div class="grid-square" @click="handleButtonClick('2')">2</div>
      <div class="grid-square" @click="handleButtonClick('3')">3</div>
      <div class="grid-square" @click="handleButtonClick('=')">=</div>
      <div class="grid-square" @click="handleButtonClick('0')">0</div>
      <div class="grid-square" @click="handleButtonClick('*')">*</div>
      <div class="grid-square" @click="handleButtonClick('/')">/</div>
      <div class="grid-square" @click="handleButtonClick('C')">C</div>
      <div class="grid-square"></div>
      <div class="grid-square" @click="handleButtonClick('.')">.</div>
      <div class="grid-square" @click="handleButtonClick('DEL')">DEL</div>
      <div class="grid-square" @click="handleButtonClick('ans')">ans</div>
    </div>

    <div id="output-area" class="output-area">
        <div class="current-expression">
          <input type="text" v-model="expression" readonly />
        </div>
        <div v-for="(calculationResult, index) in calculationResults" :key="index" class="previous-answer">
        {{ calculationResult.expression }} = {{ calculationResult.answer }}
        <div class="answer-line"></div>
      </div>
    </div>
    <div id="pagination-buttons">
      <button @click="prevPage" :disabled="currentPage === 0">Previous</button>
      <button @click="nextPage" :disabled="currentPage === totalPages - 1">Next</button>
    </div>
    <router-link id="router-link" to="/contactform">
      <button id="feedback-button">Give us feedback!</button>
    </router-link>
  </div>
  <div v-if="errorMessage" class="alert-overlay" @click="dismissAlert">
    <div class="alert-box">
      <p class="alert-message">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
import { calculateResult, getCalculationResultsByUserId } from '../api/CalculatorHooks';
import { getUserByUsername, getUserIdByUsername } from '@/api/UserHooks';
import { createCalculationRequest } from '@/utils/CreateCalculationRequest';

export default {
  created() {
    const username = localStorage.getItem('username');
    console.log('Logged in user:', username);

    this.loadCalculationResults(0);
  },

  data() {
    return {
      expression: '',
      lastOperator: '',
      calculationResults: [],
      currentPage: 0,
      pageSize: 10,
      errorMessage: '',
    }
  },
  methods: {
    logout() {
      localStorage.removeItem('username');
      this.$router.push({ path: '/' });
    },

    async pushAnswer() {
      this.expression = '';
      await this.loadCalculationResults(this.currentPage);
    },

    async loadCalculationResults(currentPage) {
      try {
        const username = localStorage.getItem('username');
        const userId = await getUserIdByUsername(username);

        const response = await getCalculationResultsByUserId(userId, currentPage, this.pageSize);
        
        this.calculationResults = response.content; 
        
        console.log('Calculation results:', this.calculationResults);
        this.currentPage = response.number;
        this.totalPages = response.totalPages;
      } catch (error) {
        console.error('Error loading calculation results:', error);
      }
    },

    async prevPage() {
      if (this.currentPage > 0) {
        await this.loadCalculationResults(this.currentPage - 1);
      }
    },

    async nextPage() {
      if (this.currentPage < this.totalPages - 1) {
        await this.loadCalculationResults(this.currentPage + 1);
      }
    },

    async calculateAndSaveResult(equation) {
      try {
        const username = localStorage.getItem('username');
        const user = await getUserByUsername(username);
        const calculationRequest = createCalculationRequest(equation, user);

        const result = await calculateResult(calculationRequest);
        console.log('Calculation result:', result);

        await this.pushAnswer();
        
      } catch (error) {
        console.error('Error calculating result:', error);
        if (error.response && error.response.status === 400) {
          this.errorMessage = 'Input a valid equation';
        } else {
          this.errorMessage = 'An error occurred. Please try again later.';
        }
      }
    },
    async handleButtonClick(value) {

      if (value === 'C') {
        this.expression = ''
        return
      } else if (value === 'DEL') {
        let temp = this.expression.substring(0, this.expression.length - 1)
        this.expression = temp
        console.log(this.expression)
        return
      } else if (value === 'ans') {
        if (this.answers.length === 0) {
          return
        } else {
          const lastAnswer = this.answers[this.answers.length - 1]
          const resultIndex = lastAnswer.lastIndexOf('=') + 1
          const result = lastAnswer.slice(resultIndex).trim()

          this.expression += result

          return
        }
      } else if (value === '=') {
        if (this.expression === '') {
          return
        }
        try {
          console.log(this.expression.toString().slice(-2))
          if (this.expression.includes('/0')) {
            this.expression = ''
            this.calculateAndSaveResult(this.expression)
          } else {
          const result = this.calculateAndSaveResult(this.expression);
          console.log('Result:', result);
          this.expression = this.expression + ' = ' + result;
          this.pushAnswer();
          }
        } catch (error) {
          console.error('Error calculating result:', error);
          this.expression = '';
          this.pushAnswer();
          this.expression = '';
        }} else {
        this.expression += value
      }
    },
    handleKeyPress(event) {
      const key = event.key
      console.log(key)

      if (key === 'Backspace') {
        event.preventDefault()
        this.handleButtonClick('DEL')
      }

      if (key === 'C') {
        this.handleButtonClick('C')
      }

      if (key === '=' || event.keyCode === 13) {
        this.handleButtonClick('=')
      }

      if (this.expression.length > 17) {
        return
      }
      // Allow only specific keys
      if (/[\d*\/\b\s=.,+\-]/.test(key)) {
        event.preventDefault() // Prevent the default behavior of the key event
        if (key === ',') {
          this.handleButtonClick('.')
        } else this.handleButtonClick(key)
      } else {
        return
      }
    },

    dismissAlert() {
      this.errorMessage = '';
    },
  },
}
</script>

<style scoped>
#router-link {
  display: flex;
  justify-content: center;
  max-width: fit-content;
  max-height: 100px;
}

#calculator {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(5, 1fr); /* Adjusted the number of rows to 5 */
  gap: 10px;
  border: 2px solid #000;
  width: 360px; /* Adjusted the width to accommodate the additional row */
  margin: 20px auto; /* Center the calculator horizontally */
  padding: 5px;
  border-radius: 5px;
  background: linear-gradient(to bottom right, #87ceeb, #ff69b4);
}

.grid-square {
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid #000;
  padding: 10px;
  border-radius: 5px;
  cursor: pointer;
  font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  font-size: 1.5rem;
}

#output-area {
  width: 240px; /* Match the calculator width */
  margin: 20px auto; /* Center the output area horizontally */
  padding: 10px;
  border: 2px solid #000;
  border-radius: 5px;
  background: linear-gradient(to bottom right, #87ceeb, #ff69b4);
  font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  font-size: 1.2rem;
  max-height: 365.96px;
  overflow-y: scroll;
}

.calculator-container {
  display: flex;
  justify-content: center; /* Center the calculator container horizontally */
  gap: 20px;
}

.calculator-container:focus {
  outline: none;
}

.previous-answer {
  margin-top: 10px;
}

.answer-line {
  height: 1px;
  background-color: #000;
  margin: 5px 0;
}

#feedback-button {
  position: absolute;
  bottom: 10px;
  right: 10px;
  width: 240px;
  padding: 8px 16px;
  border: 2px solid #000;
  border-radius: 5px;
  background-color: #4caf50;
  font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  font-size: 1.2rem;
  cursor: pointer;
}

#feedback-button:hover {
  background-color: #3e8e41;
}

.logout-button {
  position: absolute;
  top: 10px;
  left: 10px;
  padding: 8px 16px;
  background-color: #cccccc;
  border: 2px solid #000;
  border-radius: 5px;
  cursor: pointer;
  font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  font-size: 1.2rem;
}

.logout-button:hover {
  background-color: #b3b3b3;
}

.alert-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

.alert-box {
  background-color: #ff0000;
  padding: 30px;
  border-radius: 5px;
  border: 2px solid #000;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.alert-message {
  font-size: 32px;
  color: #fff;
  text-align: center;
}

</style>