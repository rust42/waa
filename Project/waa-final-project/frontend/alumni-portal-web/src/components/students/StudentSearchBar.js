import {useContext} from "react";
import {SearchBarContext} from "../providers/StudentSearchBarProvider";

const StudentSearchBar = ({children}) => {
    const {
        city,
        setCity,
        department,
        setDepartment,
        cities,
        departments,
        searchText,
        setSearchText,
        studentId,
        setStudentId,
        state,
        setState,
        states
    } = useContext(SearchBarContext);

    return <>
        <div className="container-fluid bg-primary mb-5 wow fadeIn" style={{padding: "35px"}}>
            <div className="container">
                <div className="row g-2">
                    <div className="col-md-10">
                        <div className="row g-2">
                            <div className="col-md-2">
                                <input type="text" className="form-control border-0"
                                       value={searchText}
                                       onChange={(event) => setSearchText(event.target.value)}
                                       placeholder="Name of student"/>
                            </div>
                            <div className="col-md-2">
                                <input type="text" className="form-control border-0"
                                       value={studentId}
                                       onChange={(event) => setStudentId(event.target.value)} placeholder="Student Id"/>
                            </div>
                            <div className="col-md-2">
                                <select className="form-select border-0"
                                        defaultValue={department}
                                        onChange={(event) => setDepartment(event.target.value)}>
                                    <option value="">All Departments</option>
                                    {departments.map(department => {
                                        return <option key={department.name}
                                                       value={department.name}>{department.name}</option>
                                    })}
                                </select>
                            </div>
                            <div className="col-md-2">
                                <select className="form-select border-0"
                                        defaultValue={city}
                                        onChange={(event) => setCity(event.target.value)}>
                                    <option value="">All Cities</option>
                                    {cities.map(city => {
                                        return <option key={city} value={city}>{city}</option>
                                    })}
                                </select>
                            </div>
                            <div className="col-md-2">
                                <select className="form-select border-0"
                                        defaultValue={state}
                                        onChange={(event) => setState(event.target.value)}>
                                    <option value="">All States</option>
                                    {states.map(state => {
                                        return <option key={state} value={state}>{state}</option>
                                    })}
                                </select>
                            </div>
                        </div>
                    </div>
                    {children}
                </div>
            </div>
        </div>
    </>;
};

export default StudentSearchBar;