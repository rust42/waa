import { useKeycloak } from "@react-keycloak/web"

export default function Home() {
    const { keycloak } = useKeycloak();
    return (
        <div>
            <div>User is {!keycloak?.authenticated ? 'NOT ' : ''} authenticated</div>
            <h1>Welcome to the Homepage</h1>
            
            <div>User email: {keycloak?.tokenParsed?.email} </div>
        </div>
    )
}