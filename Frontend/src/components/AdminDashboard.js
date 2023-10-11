import React from 'react'
import { Link } from 'react-router-dom'
import DoctoPop from './DoctoPop';
import { Button,Box } from '@mui/material';
import Header from './Header';
import LogoutIcon from '@mui/icons-material/Logout';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import PersonPinIcon from '@mui/icons-material/PersonPin';
import UpdateIcon from '@mui/icons-material/Update';
import DeleteIcon from '@mui/icons-material/Delete';
import { ToastContainer, toast } from 'react-toastify';
function AdminDashboard() {
  const [isOpen, setIsOpen] = React.useState(false);

  const showModal = () => {
    setIsOpen(true);
  };

  const hideModal = () => {
    setIsOpen(false);
  };
  return (
    <div style={{ backgroundColor: '#F0F0F0', minHeight: '100vh', height: '100%' }}>
         <ToastContainer style={{position:'absolute',right:'10%',top:'10%'}} />
      <Header />
      {isOpen?<DoctoPop status={isOpen} hide={hideModal}/>:""}
      <Box
          sx={{
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            marginTop:'2%'
          }}
        >
          <Box
            sx={{
              width: '50%',
              height: '800px',
              bgcolor: 'white',
              borderRadius: '20px',
              boxShadow: 20,
        
            }}
          >
          <div style={{ display: 'flex', flexDirection:'column', alignItems:'center',marginTop:'5%'}} >
        
            <div >
             <Button style={{width:400 ,margin:50,height:50, backgroundColor: '#CCF7FE',color:'black',boxShadow: '2px 2px 5px rgba(0, 0, 0, 0.3)', borderRadius: 25 }} variant="contained" color="success"  component={Link} to='/DoctorReg'><PersonAddIcon/>Add Doctor</Button> 
            </div>
        
            <div >
              <Button style={{width:400 ,margin:50,height:50, backgroundColor: '#CCF7FE',color:'black',boxShadow: '2px 2px 5px rgba(0, 0, 0, 0.3)', borderRadius: 25 }} variant="contained" color="success"  onClick={showModal}>< PersonPinIcon/>Fetch Doctor</Button>
            </div>
         
            <div >
              <Button style={{width:400 ,margin:50,height:50, backgroundColor: '#CCF7FE',color:'black',boxShadow: '2px 2px 5px rgba(0, 0, 0, 0.3)', borderRadius: 25 }} variant="contained" color="success" ><UpdateIcon/>Update Doctor</Button>
            </div>
         
            <div >
              <Button style={{width:400 ,margin:50,height:50, backgroundColor: '#CCF7FE',color:'black',boxShadow: '2px 2px 5px rgba(0, 0, 0, 0.3)', borderRadius: 25 }} variant="contained" color="success"><DeleteIcon/>Delete Doctor</Button>
            </div>
         
            <div >
              <Button style={{width:400 ,margin:50,height:50, backgroundColor: '#CCF7FE',color:'black',boxShadow: '2px 2px 5px rgba(0, 0, 0, 0.3)', borderRadius: 25 }} variant="contained" color="success" >< PersonPinIcon/>Fetch Patients</Button>
            </div>
         
      </div>
      </Box>
      </Box>
    </div>
  )
}
export default AdminDashboard;