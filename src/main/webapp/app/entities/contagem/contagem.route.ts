import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ContagemComponent } from './contagem.component';
import { ContagemDetailComponent } from './contagem-detail.component';
import { ContagemPopupComponent } from './contagem-dialog.component';
import { ContagemDeletePopupComponent } from './contagem-delete-dialog.component';

export const contagemRoute: Routes = [
    {
        path: 'contagem',
        component: ContagemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Contagems'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'contagem/:id',
        component: ContagemDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Contagems'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const contagemPopupRoute: Routes = [
    {
        path: 'contagem-new',
        component: ContagemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Contagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'contagem/:id/edit',
        component: ContagemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Contagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'contagem/:id/delete',
        component: ContagemDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Contagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
