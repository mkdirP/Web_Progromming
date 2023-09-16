<template>
  <div id="body-table">

    <div class="header">
      <div>
        <p>Wang Mingzhi P32151</p>
        <p>Variant 18000</p>
      </div>
    </div>

    <div class="body">
      <div class="choose-area">
        <div id="graph-and-ttl">
          <svg id="graph_pic" height="360" width="360" xmlns="http://www.w3.org/2000/svg" @click="addPointFromGraph">

            <line stroke="black" x1="0" y1="180" x2="360" y2="180"/>
            <line stroke="black" x1="180" y1="0" x2="180" y2="360"/>
            <polygon fill="black" points="180,0 174,15 186,15" stroke="black"/>
            <polygon fill="black" points="360,180 345,186 345,174" stroke="black"/>

            <line stroke="black" x1="240" y1="185" x2="240" y2="175"/>
            <line stroke="black" x1="300" y1="185" x2="300" y2="175"/>

            <line stroke="black" x1="60" y1="185" x2="60" y2="175"/>
            <line stroke="black" x1="120" y1="185" x2="120" y2="175"/>

            <line stroke="black" x1="175" y1="120" x2="185" y2="120"/>
            <line stroke="black" x1="175" y1="60" x2="185" y2="60"/>

            <line stroke="black" x1="175" y1="240" x2="185" y2="240"/>
            <line stroke="black" x1="175" y1="300" x2="185" y2="300"/>

            <text fill="black" x="230" y="170">1.5</text>
            <text fill="black" x="295" y="170">3</text>

            <text fill="black" x="50" y="170">-3</text>
            <text fill="black" x="105" y="170">-1.5</text>

            <text fill="black" x="190" y="65">3</text>
            <text fill="black" x="190" y="125">1.5</text>

            <text fill="black" x="190" y="245">-1.5</text>
            <text fill="black" x="190" y="305">-3</text>

            <text fill="black" x="350" y="170">X</text>
            <text fill="black" x="160" y="15">Y</text>

            <polygon id="rectangle" fill="#c777b9"
                     fill-opacity="0.6"
                     stroke="black"
                     points="180,180 300,180 300,60 180,60"/>

            <path id="circle" fill="#c777b9"
                  fill-opacity="0.6"
                  stroke="black"
                  d="M 120 180 A 60 60, 90, 0, 0, 180 240 L 180 180"/>

            <polygon id="triangle" fill="#c777b9"
                     fill-opacity="0.6"
                     stroke="black"
                     points="120,180 180,180 180,60"/>
          </svg>
        </div>

        <form id="hidden-form" style="display: none">
          <input type="text" id="pointX"/>
          <input type="text" id="pointY"/>
          <input type="text" id="pointR"/>
          <button type="submit" id="hidden-btn"></button>
        </form>

        <div>
          <form id="coordinates-form">
            <div class="checkbox-area">
              <p>X:</p>
              <a id="forXError" style="font-size: 10px; font-style: oblique; color: #4C489D"></a>
              <div id="x-group">
                <div class="row">
                  <input class="ordinary-btn" id="-5" type="button" value="-5" name="x"
                         @click="rememberX(-5); paintButtons(-5)">
                  <input class="ordinary-btn" id="-4" type="button" value="-4" name="x"
                         @click="rememberX(-4); paintButtons(-4)">
                  <input class="ordinary-btn" id="-3" type="button" value="-3" name="x"
                         @click="rememberX(-3); paintButtons(-3)">
                </div>
                <div class="row">
                  <input class="ordinary-btn" id="-2" type="button" value="-2" name="x"
                         @click="rememberX(-2); paintButtons(-2)">
                  <input class="ordinary-btn" id="-1" type="button" value="-1" name="x"
                         @click="rememberX(-1); paintButtons(-1)">
                  <input class="ordinary-btn" id="0" type="button" value="0" name="x"
                         @click="rememberX(0); paintButtons(0)">
                </div>
                <div class="row">
                  <input class="ordinary-btn" id="1" type="button" value="1" name="x"
                         @click="rememberX(1); paintButtons(1)">
                  <input class="ordinary-btn" id="2" type="button" value="2" name="x"
                         @click="rememberX(2); paintButtons(2)">
                  <input class="ordinary-btn" id="3" type="button" value="3" name="x"
                         @click="rememberX(3); paintButtons(3)">
                </div>
              </div>

              <p>Y:</p>
              <div>
                <input id="yField" type="text" autocomplete="on" name="y" maxlength="8" required="" placeholder="(-5;3)"
                       onkeyup="value=value.replace(/[^\0-9\.]/g,'')" onpaste="value=value.replace(/[^\0-9\.]/g,'')"
                       oncontextmenu = "value=value.replace(/[^\0-9\.]/g,'')"/>
              </div>

              <p>R:</p>
              <div class="y-group">
                <div class="row">
                  <input class="ordinary-btn" type="button" value="-5" name="r" disabled>
                  <input class="ordinary-btn" type="button" value="-4" name="r" disabled>
                  <input class="ordinary-btn" type="button" value="-3" name="r" disabled>
                </div>
                <div class="row">
                  <input class="ordinary-btn" type="button" value="-2" name="r" disabled>
                  <input class="ordinary-btn" type="button" value="-1" name="r" disabled>
                  <input class="ordinary-btn" type="button" value="0" name="r" disabled>
                </div>
                <div class="row">
                  <input class="ordinary-btn-R" id="r1" type="button" value="1" name="r"
                         @click="rememberR(1); paintButtonsR('r1')">
                  <input class="ordinary-btn-R" id="r2" type="button" value="2" name="r"
                         @click="rememberR(2); paintButtonsR('r2')">
                  <input class="ordinary-btn-selected-R" id="r3" type="button" value="3" name="r"
                         @click="rememberR(3); ; paintButtonsR('r3')">
                </div>
              </div>

              <div class="buttons-area">
                <button id="function-btn" type="button" class="gradient-button" @click="validateInput()">
                  Submit
                </button>
              </div>
            </div>
          </form>
        </div>

      </div>
    </div>

    <div>
      <div class="button-row">
        <button type="button" class="gradient-button" @click="logout">LogOut</button>
        <button type="button" class="gradient-button" @click="deletePoints">Reset Table</button>
      </div>
      <div class="table-area">
        <table id="main-table">
          <caption>Result Table</caption>
          <thead>
          <tr >
            <th class="col" id="col1">X</th>
            <th class="col">Y</th>
            <th class="col">R</th>
            <th class="col" id="col4">Result</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="point in points" v-bind:key="point.id">
            <td>{{ point.x }}</td>
            <td>{{ point.y }}</td>
            <td>{{ point.r }}</td>
            <td>{{ point.result }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Main",
  data() {
    return {
      x: "",
      y: "",
      r: "3",
      xGraph: "",
      yGraph: "",
      points: new Array(0)
    }
  },
  //侦听器，用于监听属性的变化并执行相应的操作
  watch: {
    r() {
      let r = parseFloat(this.r);
      let circle = document.getElementById("circle");
      let rectangle = document.getElementById("rectangle");
      let triangle = document.getElementById("triangle");
      circle.setAttribute("d", `M ${180 - 20 * r} 180 A 60 60, 90, 0, 0, 180 ${180 + 20 * r} L 180 180 `);
      triangle.setAttribute("points", `${180 - 20 * r},180 180,180 180,${180- 40 *r}`);
      rectangle.setAttribute("points", `180,180 ${180 + 40 *r},180 ${180 + 40 *r},${180- 40 *r} 180,${180- 40 *r} `);
      this.drawPoints();
    }
  },
  methods: {
    addPoints() {
      axios.post('http://localhost:8083/api/points/main', {
        x: document.getElementById('pointX').value,
        y: document.getElementById('yField').value.trim(),
        r: this.r
      }, {
        headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
      }).then(() => {
        this.getPoints();
        this.drawPoints();
      }, () => {
        this.$swal.fire({
          icon: "error",
          text: "The point couldn't be added",
          title: ":("
        });
      }).catch(() => {
        this.$router.push({name: 'error-page-expired'})
      })
    },
    getPoints() {
      axios.get('http://localhost:8083/api/points/main', {
        headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
      }).then((response) => {
        this.points = response.data;
        this.drawPoints();
      }).catch(() => {
        this.$router.push({name: 'error-page-expired'})
      })
    },

    deletePoints() {
      this.$swal.fire({
        title: ':?',
        text: "Can't recover",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes',
      }).then((result) => {
        if (result.isConfirmed) {
          axios.delete('http://localhost:8083/api/points/main', {
            headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
          }).then(() => {
            this.getPoints();
            this.$swal.fire({
              icon: "success",
              text: "Points removed",
              title: ":)"
            });
          }, () => {
            this.$swal.fire({
              icon: "error",
              text: "Failed to delete dots",
              title: ":("
            });
          }).catch(() => {
            this.$router.push({name: 'error-page-expired'})
          })}
      });
    },

    logout() {
      this.$swal.fire({
        title: ':?',
        text: "Sure to leave?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes',
      }).then((result) => {
        if (result.isConfirmed) {
          this.$swal.fire({
            icon: "success",
            text: "Bye",
            title: "Logout +ing",
          });
          this.$router.push({name: "auth-page"}, () => localStorage.clear());
        }
      });
    },

    drawPoints() {
      const svgSize = 360;
      let r = parseFloat(this.r);
      let svg = document.getElementById("graph_pic")
      let oldDots = document.querySelectorAll("circle");
      oldDots.forEach(oldDot => oldDot.parentNode.removeChild(oldDot));
      console.log(this.points.length);
      console.log(this.points)
      if (this.points.length !== 0) {
        this.points.forEach(point => {
          let newPoint = document.createElementNS("http://www.w3.org/2000/svg", "circle");
          newPoint.setAttribute("id", "target-dot");
          newPoint.setAttribute("r", "3.5");
          newPoint.setAttribute("cx", `${point.x * 40 + svgSize / 2}`);
          newPoint.setAttribute("cy", `${svgSize / 2 - point.y * 40}`);
          if (point.r === r) {
            newPoint.setAttribute("fill", point.result === "true" ? "green" : "red");
            newPoint.setAttribute("r", "4");
          } else {
            newPoint.setAttribute("fill", "black");
            newPoint.setAttribute("opacity", "0.5");
          }
          svg.appendChild(newPoint);
        })
      }
    },
    addPointFromGraph() {
      let position = getMousePosition(document.getElementById("graph_pic"), event);
      this.xGraph = ((position.x - 180) / 40 ).toFixed(2);
      this.yGraph = (( (180 - position.y)) / 40).toFixed(2);
      console.log(position.x, position.y);
      console.log(this.xGraph, this.yGraph);
      if (this.r >= 1 && this.r <= 3) {
        axios.post('http://localhost:8083/api/points/main', {
          x: this.xGraph,
          y: this.yGraph,
          r: this.r,
          // ttl: document.getElementById('ttlInput').value
        }, {
          headers: {"Authorization": "Bearer " + localStorage.getItem("jwt")}
        }).then(() => {
          this.getPoints();
          this.drawPoints();
        }, () => {
          this.$swal.fire({
            icon: "error",
            text: "No points added successfully",
            title: ":("
          });
        });
      } else {
        this.$swal.fire({
          icon: "error",
          text: "Selected the wrong radius",
          title: ":("
        });
      }

      function getMousePosition(element, event) {
        let rect = element.getBoundingClientRect();
        return {
          x: event.clientX - rect.left,
          y: event.clientY - rect.top
        };
      }
    },

    rememberX(value1) {
      document.getElementById('pointX').value = value1;
    }
    ,
    rememberR(value1) {
      document.getElementById('pointR').value = value1;
      this.r = value1;
    }
    ,
    validateInput() {
      let y;
      let yText = document.getElementById("yField");
      y = yText.value.trim();
      if (/^-?\d+\.?\d*$/.test(y)) {
        if (y >= 3 || y <= -5){
          yText.setCustomValidity("Number outside the valid range");
          this.$swal.fire({
            icon: "error",
            text: "Number outside the valid range",
            title: ":("
          });
        } else {
          yText.setCustomValidity("");
          if (document.getElementById('pointX').value !== '') {
            this.addPoints();
          } else {
            this.$swal.fire({
              icon: "error",
              text: "Didn't choose X",
              title: ":("
            });
          }
        }
      } else {
        this.$swal.fire({
          icon: "error",
          text: "Didn't input Y or wrong input",
          title: ":("
        });
      }
    },

    paintButtons(id) {
      let selected = document.querySelectorAll(".ordinary-btn-selected");
      if (selected !== null) {
        document.querySelectorAll(".ordinary-btn-selected").forEach(function (i) {
              i.classList.toggle("ordinary-btn");
              i.classList.toggle("ordinary-btn-selected");
            }
        )
      }
      document.getElementById(id).classList.toggle("ordinary-btn");
      document.getElementById(id).classList.toggle("ordinary-btn-selected");
    }
    ,
    paintButtonsR(id) {
      let selected = document.querySelectorAll(".ordinary-btn-selected-R");
      if (selected !== null) {
        document.querySelectorAll(".ordinary-btn-selected-R").forEach(function (i) {
              i.classList.toggle("ordinary-btn-R");
              i.classList.toggle("ordinary-btn-selected-R");
            }
        )
      }
      document.getElementById(id).classList.toggle("ordinary-btn-R");
      document.getElementById(id).classList.toggle("ordinary-btn-selected-R");
    }
  }
  ,
  mounted() {
    this.getPoints();
    this.drawPoints();
  }
}
</script>

<style>
@import "../assets/header.css";
@import "../assets/body.css";
@import "../assets/adaptive.css";
</style>