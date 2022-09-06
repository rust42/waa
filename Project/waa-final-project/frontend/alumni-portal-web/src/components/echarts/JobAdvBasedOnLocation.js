import ReactEcharts from "echarts-for-react";
import {useDispatch, useSelector} from "react-redux";
import {fetchJobsByLocation} from "../../reducers/EchartsSlice";
import {useEffect} from "react";

export default function JobAdvBasedOnLocation() {

    const dispatch = useDispatch();
    const jobByLocation = useSelector(state => state.echarts.jobByLocation)

    useEffect(() => {
        dispatch(fetchJobsByLocation());
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
            text: "Jobs by location",
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