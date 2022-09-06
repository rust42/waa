import ReactEcharts from "echarts-for-react";
import {useDispatch, useSelector} from "react-redux";
import {fetchJobsByLocation, fetchStudentsPerState} from "../../reducers/EchartsSlice";
import {useEffect} from "react";

export default function StudentsPerState() {

    const dispatch = useDispatch();
    const studentsPerState = useSelector(state => state.echarts.studentsPerState)

    useEffect(() => {
        dispatch(fetchStudentsPerState());
    }, [])

    const option = {
        xAxis: {
            type: 'category',
            data: studentsPerState.names,
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: studentsPerState.numbers,
                type: 'bar'
            }
        ],
        title: {
            show: true,
            text: "Students per State",
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