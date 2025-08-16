<script setup>

import axios from 'axios';
import { onMounted, ref } from 'vue';
import { ElNotification } from 'element-plus'

const BOARD_SIZE = 7;
const rows = ref(Array.from({ length: BOARD_SIZE }, (_, i) => i));
// 创建一个二维数组来存储棋盘状态，0表示空，1表示黑子，2表示白子
const boardState = ref(Array(7).fill(0).map(() => Array(7).fill(0)));
// 添加当前玩家状态
const currentPlayer = ref(0);



function luozi(i, j) {
  console.log(i, j);
  // 使用currentPlayer的值来决定放置什么颜色的棋子
  // boardState.value[i][j] = parseInt(currentPlayer.value);
  boardState.value[i][j] = currentPlayer.value;

  _sendToServer(i, j, currentPlayer.value);

}

var hostname = window.location.hostname;

function _sendToServer(x, y, color) {
  // 模拟发送数据到服务器
  console.log(`发送到服务器: 玩家 ${color} 在位置 (${x}, ${y}) 放置了棋子`);

  axios.post(
    'http://' + hostname + ':8000/api/luozi',
    {
      cellId: `${x}-${y}`,
      color: color
    })
    .then(response => {
      console.log('服务器响应:', response.data);
    })
    .catch(error => {
      console.error('发送数据到服务器时出错:', error);
    });
}




//---------------select----------------
// const radio1 = ref(1)

function selectChange(value) {
  console.log(value)
}

function lgoin() {
  console.log('登录');
  axios.post(
    'http://' + hostname + ':8000/api/login')
    .then(response => {
      console.log('登录成功:', response.data);

      if (response.data === -1) {
        notifi()
        return;
      }

      currentPlayer.value = response.data; // 假设服务器返回当前玩家

      saveLoginState(response.data);


    })
    .catch(error => {
      console.error('登录失败:', error);
    });
}


function notifi() {
  // 创建一个新的通知

  ElNotification({
    title: 'Error',
    message: '登录失败，人数已满',
    type: 'error',
  })

}


function saveLoginState(player) {
  localStorage.setItem('currentPlayer', player);
}




function logout() {
  const player = currentPlayer.value;
  
  axios.get(`http://${hostname}:8000/api/logout`, {
    params: {
      playerId: player
    }
  })
  .then(response => {
    console.log('登出成功:', response.data);
    currentPlayer.value = 0;
    localStorage.removeItem('currentPlayer');
  })
  .catch(error => {
    console.error('登出失败:', error);
  });
}

  onMounted(() => {
    // 页面加载时检查是否有登录状态
    const savedPlayer = localStorage.getItem('currentPlayer');
    if (savedPlayer) {
      currentPlayer.value = parseInt(savedPlayer);
    }
  });


</script>

<template>


  <h1>五子棋</h1>

  <el-button v-if="currentPlayer === 0" type="primary" @click="lgoin">登录</el-button>
  <el-result v-else icon="primary" :title="currentPlayer == 1 ? '黑子' : '白子'">
    <template #extra>
      <el-button type="primary" @click="logout">登出</el-button>
    </template>
  </el-result>

  <!-- <select v-model="currentPlayer">
  <option value="1">黑子</option>
  <option value="2">白子</option>
</select> -->

  <!-- <el-radio-group v-model="radio1" @change="selectChange"> 可以不写括号，写的话里面要加$event -->
  <!-- <div>
    <el-radio-group v-model="currentPlayer" @change="selectChange($event)">
     
      <el-radio-button :value="1" label="黑子"></el-radio-button>
      <el-radio-button :value="2" label="白子"></el-radio-button>

    </el-radio-group>
  </div> -->


  <div id="board" class="board">
    <div v-for="i in rows" :key="i" class="board-row">
      <div :id="i + '_' + j" v-for="j in rows" :key="j" class="board-cell" @click="luozi(i, j)">
        <div v-if="boardState[i][j] === 1" class="chess-piece" style="background-color: black;"></div>
        <div v-else-if="boardState[i][j] === 2" class="chess-piece"
          style="background-color: white; border: 1px solid #aaa;"></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.board {
  background-color: rgb(243, 242, 187);
  padding: 10px;
  display: inline-block;

}

.board-row {
  display: flex;
}

.board-cell {
  width: 50px;
  height: 50px;
  border: 1px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
}

.chess-piece {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: black;
}
</style>
