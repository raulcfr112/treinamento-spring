const mode = document.getElementById('mode_icon')

mode.addEventListener('click',()=>  {
    const form = document.getElementById('login_form');
    const main = document.getElementById('container');
    if(mode.classList.contains('fa-moon')){
        mode.classList.remove('fa-moon');
        mode.classList.add('fa-sun');
        form.classList.add('dark');
        main.classList.add('dark');
        social
        return;
    }
    mode.classList.remove('fa-sun');
    mode.classList.add('fa-moon');
    form.classList.remove('dark');
    main.classList.remove('dark');
});