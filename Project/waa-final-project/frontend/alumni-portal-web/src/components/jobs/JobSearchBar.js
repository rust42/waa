import {useContext, useState} from "react";
import {SearchJobBarContext} from "../providers/JobSearchBarProvider";
import Select from "react-select";

const JobSearchBar = ({children}) => {
    const {
        city,
        setCity,
        state,
        setState,
        cities,
        states,
        companyName,
        setCompanyName,
        tagChoosing,
        setTagChoosing,
        tagOption,
        setTagOption
    } = useContext(SearchJobBarContext);

    function chooseHandle(e) {
        setTagChoosing(e.label);
    }

    return <>
        <div className="container-fluid bg-primary mb-5 wow fadeIn" data-wow-delay="0.1s" style={{padding: "35px"}}>
            <div className="container">
                <div className="row g-2">
                    <div className="col-md-10">
                        <div className="row g-2">
                            <div className="col-md-3">
                                <input type="text" className="form-control border-0"
                                       value={companyName}
                                       onChange={(event) => setCompanyName(event.target.value)} placeholder="Company name"/>
                            </div>
                            <div className="col-md-3">
                                <select className="form-select border-0"
                                        defaultValue={city}
                                        onChange={(event) => setCity(event.target.value)}>
                                    <option value="">All Cities</option>
                                    {cities.map(city => {
                                        return <option key={city} value={city}>{city}</option>
                                    })}
                                </select>
                            </div>
                            <div className="col-md-3">
                                <Select
                                        options={tagOption} onChange={(e) => chooseHandle(e)}>
                                </Select>
                            </div>
                            <div className="col-md-3">
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
                    <div className="col-md-2">
                        {children}
                    </div>
                </div>
            </div>
        </div>
    </>;
}

export default JobSearchBar;