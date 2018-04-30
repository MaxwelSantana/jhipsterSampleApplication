import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ConfiguracaoColetorComponent } from './configuracao-coletor.component';
import { ConfiguracaoColetorDetailComponent } from './configuracao-coletor-detail.component';
import { ConfiguracaoColetorPopupComponent } from './configuracao-coletor-dialog.component';
import { ConfiguracaoColetorDeletePopupComponent } from './configuracao-coletor-delete-dialog.component';

export const configuracaoColetorRoute: Routes = [
    {
        path: 'configuracao-coletor',
        component: ConfiguracaoColetorComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConfiguracaoColetors'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'configuracao-coletor/:id',
        component: ConfiguracaoColetorDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConfiguracaoColetors'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const configuracaoColetorPopupRoute: Routes = [
    {
        path: 'configuracao-coletor-new',
        component: ConfiguracaoColetorPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConfiguracaoColetors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'configuracao-coletor/:id/edit',
        component: ConfiguracaoColetorPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConfiguracaoColetors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'configuracao-coletor/:id/delete',
        component: ConfiguracaoColetorDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ConfiguracaoColetors'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
