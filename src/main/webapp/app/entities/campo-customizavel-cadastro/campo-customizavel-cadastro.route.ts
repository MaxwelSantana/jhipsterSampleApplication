import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CampoCustomizavelCadastroComponent } from './campo-customizavel-cadastro.component';
import { CampoCustomizavelCadastroDetailComponent } from './campo-customizavel-cadastro-detail.component';
import { CampoCustomizavelCadastroPopupComponent } from './campo-customizavel-cadastro-dialog.component';
import { CampoCustomizavelCadastroDeletePopupComponent } from './campo-customizavel-cadastro-delete-dialog.component';

export const campoCustomizavelCadastroRoute: Routes = [
    {
        path: 'campo-customizavel-cadastro',
        component: CampoCustomizavelCadastroComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelCadastros'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'campo-customizavel-cadastro/:id',
        component: CampoCustomizavelCadastroDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelCadastros'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const campoCustomizavelCadastroPopupRoute: Routes = [
    {
        path: 'campo-customizavel-cadastro-new',
        component: CampoCustomizavelCadastroPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelCadastros'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'campo-customizavel-cadastro/:id/edit',
        component: CampoCustomizavelCadastroPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelCadastros'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'campo-customizavel-cadastro/:id/delete',
        component: CampoCustomizavelCadastroDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelCadastros'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
