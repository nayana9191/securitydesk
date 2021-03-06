import React, {Component} from 'react';
import axios from 'axios';
import Form from "./LNCForm";


class LateNightConveyance extends Component {
    intervalID;
    constructor(props) {
        super(props);

        this.state = {
            people: []
        };

        this.addPerson = this.addPerson.bind(this);
        this.deletePerson = this.deletePerson.bind(this);
    }

    componentDidMount() {
        this.getData();
    }


    getData = () => {
        axios.get('/getLNCEntry')
            .then(res => {
                this.setState({people: res.data});
                // call getData() again in 5 seconds
                this.intervalID = setTimeout(this.getData.bind(this), 1000);
            });
    }


    addPerson(id, name) {
        const bodyFormData = {
            name: name,
            employeeId: id
        };
        axios.post('/createLNCEntry', bodyFormData)
            .then(res => {
                this.setState({LateNightConveyance: res.data});
                console.log(this.state.LateNightConveyance);
            })
    }

    deletePerson(e, employeeId){ e.preventDefault(); {
        axios.get(`/updateLNCEntry/${employeeId}`)
            .then(res => {
                this.setState({people: res.data})
            })
    }}

    render(){
        return (
            <section className="smart-filter-content">

                <h2 className="smart-filter-heading">Employee Register</h2>

                        <div>

                            <h2 className="smart-filter-heading">Enter employee details:</h2>

                            <Form addPerson={this.addPerson} />
                        </div>

                <div>
                    <h2 className="smart-filter-heading">Employee List:</h2>
                    <table className="responsive-table">
                        <thead>
                        <tr>
                            <th scope="col">Employee-Id</th>
                            <th scope="col">Name</th>
                            <th scope="col">CheckIn Time</th>
                            <th scope="col">CheckOut</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.people.map((person, index) => {
                            return (
                        <tr key={person.name}>
                            <th scope="row" data-title="employeeId">{person.employeeId}</th>
                            <td data-title="name">{person.name}</td>
                            <td data-title="id">{person.checkInTime}</td>
                            <td data-title="checkout button"> <button type="submit" onClick={(e) => this.deletePerson(e, person.employeeId)}>Check out</button></td>
                        </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>

            </section>
        );
    }
}


export default LateNightConveyance;
