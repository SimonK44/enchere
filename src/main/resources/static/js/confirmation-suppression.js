/**
 * affichage d' une pop-up de confirmation de la suppression
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
    const confirmLink = document.getElementById('suppression');
    const popup = document.getElementById('popup');
    const yesButton = document.getElementById('yesButton');
    const noButton = document.getElementById('noButton');
    let hrefToNavigate = '';

<<<<<<< Updated upstream
    confirmLink.addEventListener('click', function(event) {
        event.preventDefault(); // Empêche le comportement par défaut du lien
        hrefToNavigate = this.href; // Stocke l'URL du lien
        popup.style.display = 'flex'; // Affiche la pop-up de confirmation
    });

    yesButton.addEventListener('click', function() {
        window.location.href = hrefToNavigate; // Redirige vers l'URL stockée
    });

    noButton.addEventListener('click', function() {
        popup.style.display = 'none';
    });
});
=======
	function confirmation() {
	    return confirm("Êtes-vous sûr de vouloir visiter ce lien ?");
	    
	}
>>>>>>> Stashed changes
