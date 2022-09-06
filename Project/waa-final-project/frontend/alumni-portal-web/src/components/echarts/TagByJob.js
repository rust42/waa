import ReactEcharts from "echarts-for-react";
import {useDispatch, useSelector} from "react-redux";
import {fetchJobsByLocation, fetchTagByJob} from "../../reducers/EchartsSlice";
import {useEffect} from "react";

export default function TagByJob() {

    const dispatch = useDispatch();
    const tagByJob = useSelector(state => state.echarts.tagByJob)

    useEffect(() => {
        dispatch(fetchTagByJob());
    }, [])

    const option = {
        xAxis: {
            type: 'category',
            data: tagByJob.names,
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: tagByJob.numbers,
                type: 'bar'
            }
        ],
        title: {
            show: true,
            text: "Total tags in job",
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