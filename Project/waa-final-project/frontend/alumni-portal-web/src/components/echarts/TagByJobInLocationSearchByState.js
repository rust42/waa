import ReactEcharts from "echarts-for-react";
import {useDispatch, useSelector} from "react-redux";
import {fetchStateOfStudent, fetchStudentsByCity, fetchTagByLocation} from "../../reducers/EchartsSlice";
import {useEffect, useState} from "react";
import Select from "react-select";
import {fetchStatesReducer} from "../../reducers/StatesSlice";

export default function TagByJobInLocationSearchByState() {

    const dispatch = useDispatch();
    const stateByJob = useSelector(state => state.states.states)
    const [stateChoosing, setStateChoosing] = useState([]);
    const tagByJobInLocation = useSelector(state => state.echarts.tagByJobInLocation)

    const [stateOption, setStateOption] = useState([]);
    useEffect(() => {
        dispatch(fetchStatesReducer());
    }, [])

    useEffect(() => {
        let statesOption = [{value: 0, label: "No State"}];
        let count = 1;
        stateByJob.forEach(state => {
            statesOption.push({
                value: count++,
                label: state,
            })
        })
        setStateOption(statesOption);
    }, [stateByJob])

    function chooseHandle(e) {
        setStateChoosing(e.label);
    }

    async function searchButtonClicked() {
        if (stateChoosing === "No State") {
        } else {
            await dispatch(fetchTagByLocation({state: stateChoosing}));
        }
    }

    return (
        <div className="container-fluid bg-primary mb-5 wow fadeIn" data-wow-delay="0.1s" style={{padding: "35px"}}>
            <div className="container">
                <div className="row g-2">
                    <div className="col-md-10">
                        <div className="row g-2">
                            <div className="col-md-3">
                                <Select
                                    options={stateOption} onChange={(e) => chooseHandle(e)}>
                                </Select>
                            </div>
                            <div className="col-md-3">
                                <button className="btn btn-dark border-0 w-100" onClick={searchButtonClicked}>Search
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="d-grid gap-3">
                <div className="p-2 bg-light border" style={{marginTop: "20px"}}>
                    <ReactEcharts option={{
                        xAxis: {
                            type: 'category',
                            data: tagByJobInLocation.names,
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                data: tagByJobInLocation.numbers,
                                type: 'bar'
                            }
                        ],
                        title: {
                            show: true,
                            text: `Tags per job in ${stateChoosing}`,
                            left: "center",
                            top: "bottom",
                        }
                    }}/>
                </div>
            </div>
        </div>
    );
}