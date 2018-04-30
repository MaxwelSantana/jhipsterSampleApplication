import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TipoConfiguracaoComponent } from './tipo-configuracao.component';
import { TipoConfiguracaoDetailComponent } from './tipo-configuracao-detail.component';
import { TipoConfiguracaoPopupComponent } from './tipo-configuracao-dialog.component';
import { TipoConfiguracaoDeletePopupComponent } from './tipo-configuracao-delete-dialog.component';

export const tipoConfiguracaoRoute: Routes = [
    {
        path: 'tipo-configuracao',
        component: TipoConfiguracaoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoConfiguracaos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tipo-configuracao/:id',
        component: TipoConfiguracaoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoConfiguracaos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoConfiguracaoPopupRoute: Routes = [
    {
        path: 'tipo-configuracao-new',
        component: TipoConfiguracaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoConfiguracaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tipo-configuracao/:id/edit',
        component: TipoConfiguracaoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoConfiguracaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tipo-configuracao/:id/delete',
        component: TipoConfiguracaoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TipoConfiguracaos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
