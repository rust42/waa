import {BrowserRouter, Route, Routes} from "react-router-dom";
import Nav from "./components/Nav";
import Home from "./components/Home";
import Students from "./components/students/Students";
import Student from "./components/students/Student";
import ApplyJob from "./components/students/ApplyJob";
import Faculties from "./components/faculties/Faculties";
import Faculty from "./components/faculties/Faculty";
import LatestJob from "./components/jobs/LatestJob";
import EditJob from "./components/jobs/EditJob";
import NewJob from "./components/jobs/NewJob";
import JobDetail from "./components/jobs/JobDetail"
import NewJobExperience from "./components/students/NewJobExperience";
import MyJobs from "./components/jobs/MyJobs";
import JobApplications from "./components/jobApplications/JobApplications";
import Jobs from "./components/jobs/Jobs";
import Comments from "./components/students/Comments";
import EditFaculty from "./components/faculties/EditFaculty";
import JobApplication from "./components/jobApplications/JobApplication";
import EditStudent from "./components/students/EditStudent";
import JobExperienceHistories from "./components/students/JobExperienceHistories";
import Echarts from "./components/echarts/Echarts";
import RecentlyAppliedJobs from "./components/jobs/RecentlyAppliedJobs";

const Router = () => {
    return <BrowserRouter>
        <Nav/>

        <Routes>
            <Route path="/" element={<Home/>}/>

            <Route path="/students">
                <Route index element={<Students/>}/>
                <Route path=":id" element={<Student/>}/>
                <Route path=":id/jobExperiencesDetail" element={<JobExperienceHistories/>}/>
                <Route path=":studentId/comments" element={<Comments/>}/>
                <Route path=":id/edit" element={<EditStudent/>}/>
                <Route path="jobHistory/new" element={<NewJobExperience/>}/>
            </Route>

            <Route path="/faculties">
                <Route index element={<Faculties/>}/>
                <Route path=":id" element={<Faculty/>}/>
                <Route path=":id/edit" element={<EditFaculty/>}/>
            </Route>

            <Route path="/jobs">
                <Route index element={<Jobs/>}/>
                <Route path="latest" element={<LatestJob/>}/>
                <Route path=":id" element={<JobDetail/>}/>
                <Route path=":id/apply" element={<ApplyJob/>}/>

                <Route path=":id/edit" element={<EditJob/>}/>
                <Route path="new" element={<NewJob/>}/>
                <Route path="myJobs" element={<MyJobs/>}/>
                <Route path=":jobId/jobApplications" element={<JobApplications/>}/>
                <Route path="recentlyAppliedJobs" element={<RecentlyAppliedJobs/>}/>
            </Route>

            <Route path="/jobApplications">
                <Route path=":id" element={<JobApplication />}/>
            </Route>
            <Route path="/echarts">
                <Route index element={<Echarts/>}/>
            </Route>
        </Routes>
    </BrowserRouter>;
};

export default Router;