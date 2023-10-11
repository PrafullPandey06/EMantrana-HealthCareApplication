import { Button, Select, MenuItem, TextField,Input,InputLabel } from '@mui/material';
import { useState } from 'react';
import React from 'react'
import Header from './Header';
import { Grid} from '@mui/material'
import { useNavigate } from 'react-router-dom';
import PatientServices from '../services/PatientServices';
import { ToastContainer, toast } from 'react-toastify';

function EditProfilePage() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
      Fname: '',
      Lname: '',
      Email: '',
      dateOfBirth: ''
    });
  
      let jwt=null;
            jwt=localStorage.getItem('patientauthenticate');
            jwt="Bearer "+jwt;
            const config = {
            headers:{
                'Authorization':jwt
            }
            };
  
    const handleFormChange = (e) => {
      const { id, value } = e.target;
      setFormData({ ...formData, [id]: value });
    };
  
    // const handleGenderChange = (e) => {
    //   setFormData({ ...formData, gender: e.target.value });
    // };
    const handleSubmit = (e) => {
      e.preventDefault();
  
      // Validate form input before submission
      if (!formData.Fname && !formData.Lname && !formData.Email  &&  !formData.dateOfBirth) {
       
         // alert('Please fill atleast one feild');
          toast.error('Please fill atleast one feild');
        return;
      }
     
      PatientServices.updatepatientprofile(formData,config).then((response) => {
          console.log(formData);
          toast.success('Profile updated successfully');
          setTimeout((() => {
               navigate("/PatientDashboard");
          }),5000)
     
    })
          .catch((error) => {
           toast.error(error);
        console.log(error);
      });
  
      // Handle form submission here
      console.log('Submitted form:', formData);
  
      // Reset form fields
      setFormData({
        Fname: '',
        Lname: '',
        Email: '',
        dateOfBirth: ''
      });

    };
  

  return (

      <div style={{ background: ' linear-gradient(135deg, #FFA07A, #87CEFA)', height: '100vh' }} >
             <ToastContainer style={{position:'absolute',right:'10%',top:'10%'}} />
        <form  onSubmit={handleSubmit}>
        <Header />
        <div className='header' style={{ display:'flex',justifyContent: 'center',color:'white',padding: 80}}><h1>Edit Profile</h1></div>
        <Grid container spacing={1}>
          <Grid item xs={5} sm={6}>
            <TextField
              style={{ marginLeft:'45%',marginBottom:'5%',width: '50%', backgroundColor: '#faebd7' }}
              id="Fname"
              label="Enter First Name"
              value={formData.Fname}
              onChange={handleFormChange}
            />
          </Grid>
          <Grid item xs={5} sm={6}>
            <TextField
              style={{ marginRight:'45%',marginBottom:'5%',width: '50%', backgroundColor: '#faebd7' }}
              id="Lname"
              label="Enter Last Name"
              value={formData.Lname}
              onChange={handleFormChange}
            />
          </Grid>
          </Grid>
          <Grid container spacing={1}>
             <Grid item xs={5} sm={6}>
             <TextField
              style={{ marginLeft:'45%',marginBottom:'5%',width: '50%', backgroundColor: '#faebd7' }}
              id="Email"
              label="Enter Email"
              value={formData.Email}
              onChange={handleFormChange}
            />
            {/* <Select
             style={{ marginLeft:'45%',marginBottom:'5%',width: '50%', backgroundColor: '#faebd7' }}
              id="gender"
              value={formData.gender}
              label="Gender"
              onChange={handleGenderChange}
            >
              <MenuItem value="male">Male</MenuItem>
              <MenuItem value="female">Female</MenuItem>
              <MenuItem value="other">Other</MenuItem>
            </Select> */}
          </Grid>
          <Grid item xs={5} sm={6}>
            <TextField type="date" 
              style={{ marginRight:'45%',marginBottom:'5%',width: '50%',height:'60%', backgroundColor: '#faebd7' }}
              id="dateOfBirth"
              value={formData.dateOfBirth}
              onChange={handleFormChange}
            />
          </Grid>
          </Grid>

          <Grid container spacing={2} justifyContent="center">
          <Grid item xs={12} sm={6} md={4}>
          <Button 
          style={{ marginLeft:'31%',width: '30%', marginTop:'5%',height: '50px', backgroundColor: 'darkgray', borderRadius: '25px' }}
          variant="contained"
          color="success"
          type="submit"
          >
          <h3 style={{ color: 'black' }}>SAVE</h3>
          </Button>
          </Grid>
          </Grid>
        </form> 
    </div>
  )
}

export default EditProfilePage;