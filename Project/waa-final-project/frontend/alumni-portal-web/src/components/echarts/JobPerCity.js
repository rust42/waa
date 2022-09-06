import ReactEcharts from "echarts-for-react";
import {useDispatch, useSelector} from "react-redux";
import {fetchJobsByCity, fetchJobsByLocation} from "../../reducers/EchartsSlice";
import {useEffect} from "react";

export default function JobPerCity() {

    const dispatch = useDispatch();
    const jobByLocation = useSelector(state => state.echarts.jobsByCity)

    useEffect(() => {
        dispatch(fetchJobsByCity());
    }, [])

    const option = {
        xAxis: {
            type: 'category',
            data: jobByLocation.names,
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: jobByLocation.numbers,
                type: 'bar'
            }
        ],
        title: {
            show: true,
            text: "Jobs per city",
            left: "center",
            top: "bottom",
        }
    };
    return (
        <div className="d-grid gap-3">
            <div className="p-2 bg-light border" style={{marginTop: "20px"}}>
                <ReactEcharts option={option}/>
            </div>
        </div>
    );
}