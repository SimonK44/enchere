/**
 * affichage d' une pop-up de confirmation de la suppression
 * 
 */
	const boutonSuppression = document.getElementById("suppression");
	
	boutonSuppression.addEventListener("click", confirmation);

	function confirmation() {		
	    return confirm("Êtes-vous sûr de vouloir visiter ce lien ?");	    
	}