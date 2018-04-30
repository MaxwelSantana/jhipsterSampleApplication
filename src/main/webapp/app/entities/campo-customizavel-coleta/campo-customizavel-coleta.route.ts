import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CampoCustomizavelColetaComponent } from './campo-customizavel-coleta.component';
import { CampoCustomizavelColetaDetailComponent } from './campo-customizavel-coleta-detail.component';
import { CampoCustomizavelColetaPopupComponent } from './campo-customizavel-coleta-dialog.component';
import { CampoCustomizavelColetaDeletePopupComponent } from './campo-customizavel-coleta-delete-dialog.component';

export const campoCustomizavelColetaRoute: Routes = [
    {
        path: 'campo-customizavel-coleta',
        component: CampoCustomizavelColetaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelColetas'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'campo-customizavel-coleta/:id',
        component: CampoCustomizavelColetaDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelColetas'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const campoCustomizavelColetaPopupRoute: Routes = [
    {
        path: 'campo-customizavel-coleta-new',
        component: CampoCustomizavelColetaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelColetas'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'campo-customizavel-coleta/:id/edit',
        component: CampoCustomizavelColetaPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelColetas'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'campo-customizavel-coleta/:id/delete',
        component: CampoCustomizavelColetaDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CampoCustomizavelColetas'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
