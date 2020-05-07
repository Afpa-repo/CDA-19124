$(document).ready(function(){
    /* au chargement de la page de création ou modification d'utilisateur */
    if( $('input[id=users_admin_type').is(':checked') || $('input[id=users_type').is(':checked')){    //si c'est un particulier
        $('#siret').hide(); //cache le siret
    } else {        //si c'est une entreprise
        $('#siret').show();    //affiche le siret
    }
    
    /* pour la page admin  */
    $('input[id=users_admin_type]').click(function(){   //quand le type change
        choice = $('input[id=users_admin_type').is(':checked')  //récupère la valeur cochée
        if(choice){   //si le module est éteint
            $('#siret').hide(); //bloque le champ siret
            $('#users_admin_siret').val('');   //supprime l'éventuel texte dans le champ
        }else{  //si il est allumé
            $('#siret').show();    //débloque le champ
        }
    });

    /* pour la page utilisateur simple */
    $('input[id=users_type]').click(function(){ //quand le type change
        choice = $('input[id=users_type').is(':checked')  //récupère la valeur cochée
        if(choice){   //si le module est éteint
            $('#siret').hide(); //bloque le champ siret
            $('#users_siret').val('');   //supprime l'éventuel texte dans le champ
        }else{  //si il est allumé
            $('#siret').show();    //débloque le champ
        }
    });

    /* pour l'inscription */
    $('input[id=registration_type]').click(function(){ //quand le type change
        choice = $('input[id=registration_type').is(':checked')  //récupère la valeur cochée
        if(choice){   //si le module est éteint
            $('#siret').hide(); //bloque le champ siret
            $('#registration_siret').val('');   //supprime l'éventuel texte dans le champ
        }else{  //si il est allumé
            $('#siret').show();    //débloque le champ
        }
    });


})