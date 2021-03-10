package wzy.com.demo.dto;


public class GitHubUser {

    private String login;
    private Long id;
    private String node_id;

    public GitHubUser() {
    }

    public GitHubUser(String login, Long id, String node_id) {
        this.login = login;
        this.id = id;
        this.node_id = node_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", node_id='" + node_id + '\'' +
                '}';
    }
}
