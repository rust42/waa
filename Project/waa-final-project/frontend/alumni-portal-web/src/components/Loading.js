export default function Loading({children}){
    return <div className="col text-center">
        <span className=" spinner-border text-primary" role="status">
            {children}
        </span>
    </div>;
}