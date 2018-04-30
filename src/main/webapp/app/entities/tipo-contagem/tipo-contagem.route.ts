import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TipoContagemComponent } from './tipo-contagem.component';
import { TipoContagemDetailComponent } from './tipo-contagem-detail.component';
import { TipoContagemPopupComponent } from './tipo-contagem-dialog.component';
import { TipoContagemDeletePopupComponent } from './tipo-contagem-delete-dialog.component';

export const tipoContagemRoute: Routes = [
    {
        path: 'tipo-contagem',
        component: TipoContagemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoContagems'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tipo-contagem/:id',
        component: TipoContagemDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoContagems'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoContagemPopupRoute: Routes = [
    {
        path: 'tipo-contagem-new',
        component: TipoContagemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoContagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tipo-contagem/:id/edit',
        component: TipoContagemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoContagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tipo-contagem/:id/delete',
        component: TipoContagemDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoContagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
