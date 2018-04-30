import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { MapeamentoComponent } from './mapeamento.component';
import { MapeamentoDetailComponent } from './mapeamento-detail.component';
import { MapeamentoPopupComponent } from './mapeamento-dialog.component';
import { MapeamentoDeletePopupComponent } from './mapeamento-delete-dialog.component';

export const mapeamentoRoute: Routes = [
    {
        path: 'mapeamento',
        component: MapeamentoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mapeamentos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'mapeamento/:id',
        component: MapeamentoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mapeamentos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mapeamentoPopupRoute: Routes = [
    {
        path: 'mapeamento-new',
        component: MapeamentoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mapeamentos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mapeamento/:id/edit',
        component: MapeamentoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mapeamentos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mapeamento/:id/delete',
        component: MapeamentoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Mapeamentos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
