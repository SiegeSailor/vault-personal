import React, { Component } from "react";
import './App.css';

import AddForm from './AddForm';

import Spinner from "./Spinner"

// Uncomment the line below to use your own backend server

// const baseServerURL = "http://localhost:3333";

// Currently backend server set to remote server

const baseServerURL = "https://sk-mern01.herokuapp.com";

class App extends Component {
 
    constructor(props) {
      super(props);
      this.state = {courses: [], loading: false};

      // binding required for 'this' to work in callback

      this.getCourses   = this.getCourses.bind(this);
      this.deleteCourse = this.deleteCourse.bind(this);
      this.addCourse    = this.addCourse.bind(this);

    }
 

    componentDidMount() {

      // Get current list of courses

      this.getCourses();

      // Refresh API call every 10 seconds

      this.interval = setInterval(() => {
        this.refreshCourses();

      }, 10000);

    }

    componentWillUnmount() {
      clearInterval(this.interval);
    }

    setLoading(value) {
      this.setState({loading: value})
    }

    isLoading() {
      return this.state.loading;
    }

    // Get Current list of courses

    getCourses() {

      const url = `${baseServerURL}/api/courses`;
     
      this.setLoading(true);
      
      fetch(url)
        .then(response => response.json())   
        .then(data => {
          this.setState({courses: data});
          this.setLoading(false);
        })
        .catch(error => console.error(error));

    }

    // Delete a course

    async deleteCourse(event) {
      
      const url = `${baseServerURL}/api/course/${event.target.id}`;
      const options = {
        method: 'DELETE',
        mode: 'cors'
      };

      this.setLoading(true);

      let response =  await fetch(url, options);
      let data =      await response.json();
      this.setState({courses: data});

      this.setLoading(false);

    }


    // Add a course (AddForm provides the newData through callback)

   async addCourse(newData) {
    
      const url = `${baseServerURL}/api/course`;
      const options = {
        method: 'POST',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(newData)
      };

      this.setLoading(true);

      let response = await fetch(url, options);
      let data =     await response.json();
      this.setState({courses: data});

      this.setLoading(false);

    }

    async refreshCourses() {
      
      const url = `${baseServerURL}/api/courses`;
      let response = await fetch(url);
      let data =     await response.json();

      if (data.length !== this.state.courses.length) {
        this.setState({courses: data});
      }

    }


    // UI

    render() {

      return this.isLoading() ? 
      
        (
          <div className="container">
            <Spinner />
          </div>
        ) :

       ( 
          <div className="container">

            <header className="App-header">
              <button className="btn btn-primary" onClick={this.getCourses}>Get Courses</button>
            </header>

            <ul className="list-group">
              {
                this.state.courses.map(c => (
                  <li className="list-group-item" key={c.id}> 
                    <button className="btn btn-danger buttonPad" 
                        onClick={this.deleteCourse} id={c.id}>X</button>
                    {c.id} - {c.name}
                  </li>
                ))
              }
            </ul>

            <AddForm onSubmit={this.addCourse} />

            <hr/>

          </div>
        
      );
    }
}

export default App;


