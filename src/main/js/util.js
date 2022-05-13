async function logout() {
    localStorage.setItem("authToken", null);
    localStorage.setItem("isLoggedIn", false);
    localStorage.clear;

    window.location.href = "./index.html";
}