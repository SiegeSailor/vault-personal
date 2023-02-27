import React, { Component } from "react";
import './AddForm.css';


class AddForm extends Component {
 
    constructor(props) {
        super(props);
        this.state = {newCourseName:'', newCourseId:''};

        this.updateName     = this.updateName.bind(this);
        this.updateId       = this.updateId.bind(this);
        this.handleSubmit   = this.handleSubmit.bind(this);

    }

    componentDidMount(){
        
    }

    updateId(e) {
        this.setState({newCourseId: e.target.value});
    }

    updateName(e) {
        this.setState({newCourseName: e.target.value});
    }

    handleSubmit(event) {
        event.preventDefault();
        this.props.onSubmit(
            {cid: this.state.newCourseId, cname: this.state.newCourseName});
        this.setState({newCourseName:'', newCourseId:''});
    }


  render() {
    return ( 

        <div className="container">

            <h3>Add a Course...</h3>
            <form  onSubmit={this.handleSubmit}>
                    <div className="form-group row">
                        <label htmlFor="id1" className="col-sm-2 col-form-label">Course Id</label>
                        <div className="col-sm-10">
                            <input type="text" value={this.state.newCourseId}
                                    onChange= {this.updateId}
                                    placeholder="Enter Course Id..." 
                                    className="form-control"
                                    id="id1" required/>
                        </div>
                        
                    </div>

                    <div className="form-group row">
                        <label htmlFor="id2" className="col-sm-2 col-form-label">Course Name</label>
                        <div className="col-sm-10">
                            <input type="text" value={this.state.newCourseName}
                                onChange={this.updateName}
                                placeholder="Enter Course Name..." 
                                className="form-control"
                                id="id2"
                                required />
                        </div>
                        
                    </div>

                    <div className="form-group row">
                        <div className="col-sm-10">
                        <button type="submit" className="btn btn-primary">Add!</button>
                        </div>
                    </div>

    	    </form>
        
        </div>
      
    );
  }
}

export default AddForm;


